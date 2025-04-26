package cl.smartjob.service;

 
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Service;

import cl.smartjob.entity.Phone;
import cl.smartjob.entity.User;
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
  
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;

    public Mono<UserResponse> registrarUsuario(UserRequest userRequest) {
    	String email=userRequest.getEmail();
    	return userRepository.findByEmail(email)
    	        .flatMap(existingUser -> {
    	            // Usuario existente, actualiza
    	            existingUser.setModified(LocalDateTime.now());
    	            existingUser.setToken(UUID.randomUUID().toString());
    	            existingUser.setActive(true);
    	            existingUser.setName(userRequest.getName());
    	            existingUser.setPassword(userRequest.getPassword());
    	            
    	            //Limpio los telefonos actuales:
    	            existingUser.setPhones(new ArrayList<>());
    	            userRequest.getPhones().stream().forEach(p->{
    	            	existingUser.getPhones().add(Phone.builder()
    	            			.id(existingUser.getId())
    	            			.citycode(p.getCitycode())
    	            			.countrycode(p.getCountrycode())
    	            			.number(p.getNumber())
    	            			.build());
    	            });
    	            
    	            
    	            return userRepository.save(existingUser).flatMap(u->{
    	            	u.getPhones().stream().forEach(p->{
    	            		phoneRepository.insert(UUID.randomUUID(), p.getNumber(), p.getCitycode(), p.getCountrycode(), p.getUserId());
    	            	});
    	            	return Mono.just(u);
    	            });
    	        })
    	        .switchIfEmpty(
    	            // Usuario nuevo, crea
    	            Mono.defer(() -> {
    	                User newUser = new User();
    	                UUID id= UUID.randomUUID();
    	                newUser.setId(id);
    	                newUser.setCreated(LocalDateTime.now());
    	                newUser.setModified(LocalDateTime.now());
    	                newUser.setEmail(userRequest.getEmail());
    	                newUser.setName(userRequest.getName());
    	                newUser.setPassword(userRequest.getPassword());
    	                newUser.setToken(UUID.randomUUID().toString());
    	                newUser.setActive(true);
    	                 
    	                newUser.setPhones(new ArrayList<>());
    	             
    	                return userRepository.insert(
    	                        newUser.getId(),
    	                        newUser.getName(),
    	                        newUser.getEmail(),
    	                        newUser.getPassword(),
    	                        newUser.getCreated(),
    	                        newUser.getModified(),
    	                        newUser.getToken(),
    	                        newUser.isActive()
    	                    ).thenReturn(newUser).flatMap(p->{
    	                    	userRequest.getPhones().stream().forEach(phone->{
    	                    		phoneRepository.insert(UUID.randomUUID(), phone.getNumber(), phone.getCitycode(), phone.getCountrycode(), p.getId());    	        	       
    	        	            });
    	                    	return Mono.just(p);
    	                    });
    	            })
    	        )
    	        .map(savedUser -> { 
    	        	UserResponse userResponse= UserResponse.builder()
    	            .email(savedUser.getEmail())
    	            .id(savedUser.getId())
    	            .name(savedUser.getName())
    	            .created(savedUser.getCreated())
    	            .modified(savedUser.getModified())
    	            .lastLogin(savedUser.getLastLogin())
    	            .token(savedUser.getToken())
    	            .isactive(savedUser.isActive())
    	            .phones(new ArrayList<>())
    	            .build();
    	        	
    	        	savedUser.getPhones().stream().forEach(p->{
    	        		userResponse.getPhones().add(PhoneResponse.builder()
    	        				.citycode(p.getCitycode())
    	        				.countrycode(p.getCountrycode())
    	        				.number(p.getNumber())
    	        				.build());
    	        	});
    	        	
    	        	return userResponse;
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
            .switchIfEmpty(Mono.error(new UnauthorizedException("Credenciales inválidas")));
    }

    
    
    

    /*
    public Flux<UserResponse> listarUsuarios() {
        return repository.findAll() 
            .flatMap(user -> { 
                return phoneRepository.findByUserId(user.getId())  // Esta consulta devuelve un Flux<Phone>
                    .collectList() // Recogemos los teléfonos en una lista de tipo List<Phone>
                    .map(phones -> {  // Transformamos la lista de Phone en una lista de PhoneResponse
                        List<PhoneResponse> phoneResponses = phones.stream()
                            .map(p -> PhoneResponse.builder()
                                .number(p.getNumber())
                                .citycode(p.getCitycode())
                                .countrycode(p.getCountrycode())
                                .build())
                            .collect(Collectors.toList());  // Mapeamos a una lista de PhoneResponse

                        // Creamos el UserResponse con los teléfonos mapeados
                        return UserResponse.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .email(user.getEmail())
                            .created(user.getCreated())
                            .modified(user.getModified())
                            .lastLogin(user.getLastLogin())
                            .token(user.getToken())
                            .isactive(user.isActive())
                            .phones(phoneResponses) // Asignamos la lista de PhoneResponse
                            .build();
                    });  // Esto devuelve un Mono<UserResponse>
            });
    }
*/








	
}
