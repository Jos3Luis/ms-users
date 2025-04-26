package cl.smartjob.ms_users;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import cl.smartjob.controller.UserController;
import cl.smartjob.pojo.LoginRequest;
import cl.smartjob.pojo.LoginResponse;
import cl.smartjob.pojo.UserRequest;
import cl.smartjob.pojo.UserResponse;
import cl.smartjob.service.UsuarioService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(UserController.class)
public class UserControllerTest {

	@MockBean
	private UsuarioService usuarioService;

	@InjectMocks
	private UserController userController;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testRegistrarUsuario() {

		UserRequest userRequest = UserRequest.builder().name("Juan").email("juan@example.com").password("password123")
				.phones(new ArrayList<>()).build();

		UserResponse userResponse = UserResponse.builder().name("Juan").email("juan@example.com").isactive(true)
				.phones(new ArrayList<>()).build();

		when(usuarioService.registrarUsuario(userRequest)).thenReturn(Mono.just(userResponse));

		webTestClient.post().uri("/api/usuarios/register").contentType(MediaType.APPLICATION_JSON)
				.bodyValue(userRequest).exchange().expectStatus().isAccepted().expectBody(UserResponse.class)
				.isEqualTo(userResponse);

		verify(usuarioService, times(1)).registrarUsuario(userRequest);
	}

	@Test
	void testListarUsuarios() {
		UserResponse userResponse1 = UserResponse.builder().name("Juan").email("juan@example.com").isactive(true)
				.phones(new ArrayList<>()).build(); // Usando build()

		UserResponse userResponse2 = UserResponse.builder().name("Maria").email("maria@example.com").isactive(true)
				.phones(new ArrayList<>()).build();

		when(usuarioService.listarUsuarios()).thenReturn(Flux.just(userResponse1, userResponse2));

		webTestClient.get().uri("/api/usuarios/listar").exchange().expectStatus().isOk()
				.expectBodyList(UserResponse.class).contains(userResponse1, userResponse2);

		verify(usuarioService, times(1)).listarUsuarios();
	}

	@Test
	void testLogin() {
		LoginRequest loginRequest = LoginRequest.builder().email("juan@example.com").password("password123").build(); // Usando
																														// build()

		LoginResponse loginResponse = LoginResponse.builder().token("fake-token").message("Bienvenido Juan").build(); // Usando
																														// build()

		when(usuarioService.login(loginRequest)).thenReturn(Mono.just(loginResponse));

		webTestClient.post().uri("/api/usuarios/login").contentType(MediaType.APPLICATION_JSON).bodyValue(loginRequest)
				.exchange().expectStatus().isOk().expectBody(LoginResponse.class).isEqualTo(loginResponse);

		verify(usuarioService, times(1)).login(loginRequest);
	}

}