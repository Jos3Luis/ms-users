package cl.smartjob.service;

 
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cl.smartjob.entity.Phone;
import cl.smartjob.entity.User;
import cl.smartjob.errors.EmailAlreadyExistsException;
import cl.smartjob.errors.UnauthorizedException;
import cl.smartjob.pojo.LoginRequest;
import cl.smartjob.pojo.LoginResponse;
import cl.smartjob.pojo.PhoneResponse;
import cl.smartjob.pojo.UserRequest;
import cl.smartjob.pojo.UserResponse;
import cl.smartjob.repository.PhoneRepository;
import cl.smartjob.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{
	
	@Value("${app.validation.message.login}")
	private String mensajeErrorLogin;
	
	@Value("${app.validation.message.email}")
	private String mensajeErrorEmailExist;
  
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;

    public Mono<UserResponse> registrarUsuario(UserRequest userRequest) {
        String email = userRequest.getEmail();

        return userRepository.findByEmail(email)
            .flatMap(existingUser -> {
                // Si el correo ya existe, lanzamos un error
                return Mono.error(new EmailAlreadyExistsException(mensajeErrorEmailExist));
            })
            .switchIfEmpty(
                Mono.defer(() -> {
                    UUID userId = UUID.randomUUID();
                    LocalDateTime now = LocalDateTime.now();

                    User newUser = User.builder()
                    	    .id(userId)
                    	    .created(now)
                    	    .modified(now)
                    	    .email(userRequest.getEmail())
                    	    .name(userRequest.getName())
                    	    .password(userRequest.getPassword())
                    	    .token(UUID.randomUUID().toString())
                    	    .isActive(true)
                    	    .phones(new ArrayList<>())
                    	    .build();
                    
                    return userRepository.insert(
                            newUser.getId(),
                            newUser.getName(),
                            newUser.getEmail(),
                            newUser.getPassword(),
                            newUser.getCreated(),
                            newUser.getModified(),
                            newUser.getToken(),
                            newUser.isActive()
                        )
                        .thenMany(
                            Flux.fromIterable(userRequest.getPhones())
                                .flatMap(phoneReq -> {
                                    Phone phone = Phone.builder()
                                        .id(UUID.randomUUID())
                                        .userId(newUser.getId())
                                        .number(phoneReq.getNumber())
                                        .citycode(phoneReq.getCitycode())
                                        .countrycode(phoneReq.getCountrycode())
                                        .build();

                                    newUser.getPhones().add(phone);

                                    return phoneRepository.insert(
                                    	    phone.getId(),
                                    	    phone.getNumber(),
                                    	    phone.getCitycode(),
                                    	    phone.getCountrycode(),
                                    	    phone.getUserId()
                                    	);
                                })
                        )
                        .then(Mono.just(newUser));
                })
            )
            .map(userObj -> {
                User savedUser = (User) userObj;

                UserResponse response = UserResponse.builder()
                    .id(savedUser.getId())
                    .email(savedUser.getEmail())
                    .name(savedUser.getName())
                    .created(savedUser.getCreated())
                    .modified(savedUser.getModified())
                    .lastLogin(savedUser.getLastLogin())
                    .token(savedUser.getToken())
                    .isactive(savedUser.isActive())
                    .phones(new ArrayList<>())
                    .build();

                savedUser.getPhones().forEach(p -> {
                    response.getPhones().add(
                        PhoneResponse.builder()
                            .number(p.getNumber())
                            .citycode(p.getCitycode())
                            .countrycode(p.getCountrycode())
                            .build()
                    );
                });

                return response;
            });
    }
 
    public Flux<UserResponse> listarUsuarios() {
        return userRepository.findAll()
            .flatMap(user ->
                phoneRepository.findByUserId(user.getId())
                    .map(phone -> PhoneResponse.builder()
                        .citycode(phone.getCitycode())
                        .countrycode(phone.getCountrycode())
                        .number(phone.getNumber())
                        .build())
                    .collectList()
                    .map(phoneResponses -> UserResponse.builder()
                        .name(user.getName())
                        .id(user.getId())
                        .email(user.getEmail())
                        .created(user.getCreated())
                        .modified(user.getModified())
                        .lastLogin(user.getLastLogin())
                        .token(user.getToken())
                        .isactive(user.isActive())
                        .phones(phoneResponses)
                        .build())
            );
    }

    public Mono<LoginResponse> login(LoginRequest loginRequest) {
        return userRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword())
            .flatMap(user -> {
                user.setLastLogin(LocalDateTime.now());
                return userRepository.save(user)
                    .map(savedUser -> LoginResponse.builder()
                        .message("Bienvenido " + savedUser.getName())
                        .token(savedUser.getToken())
                        .build());
            })
            .switchIfEmpty(Mono.error(new UnauthorizedException(mensajeErrorLogin)));
    }
 
	
}
