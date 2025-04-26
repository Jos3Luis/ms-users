package cl.smartjob.pojo;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserRequest
 */

//@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-23T17:09:14.426839900-05:00[America/Lima]", comments = "Generator version: 7.9.0")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

	@NotBlank(message = "El nombre es obligatorio")
	private String name;
	@NotBlank(message = "El correo es obligatorio")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "El correo no tiene un formato válido")
	private String email;
	@NotBlank(message = "La contraseña es obligatoria")
	private String password;

	private List<PhoneRequest> phones = new ArrayList<>();

}
