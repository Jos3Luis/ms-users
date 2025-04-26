package cl.smartjob.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cl.smartjob.pojo.LoginRequest;
import cl.smartjob.pojo.LoginResponse;
import cl.smartjob.pojo.UserRequest;
import cl.smartjob.pojo.UserResponse;
import cl.smartjob.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UserController {

	private final UsuarioService service;

	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Mono<UserResponse> registrarUsuario(@Valid @RequestBody UserRequest usuario) {
		return service.registrarUsuario(usuario);
	}

	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<UserResponse> listarUsuarios() {
		return service.listarUsuarios();
	}

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		return service.login(loginRequest);
	}
}