package cl.smartjob.service;

import cl.smartjob.pojo.LoginRequest;
import cl.smartjob.pojo.LoginResponse;
import cl.smartjob.pojo.UserRequest;
import cl.smartjob.pojo.UserResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsuarioService {
	public Mono<UserResponse> registrarUsuario(UserRequest userRequest);
	public Flux<UserResponse> listarUsuarios();
	public Mono<LoginResponse> login(LoginRequest loginRequest);
}
