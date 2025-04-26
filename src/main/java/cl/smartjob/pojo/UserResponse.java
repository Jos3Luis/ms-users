package cl.smartjob.pojo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserResponse
 */

//@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-23T17:09:14.426839900-05:00[America/Lima]", comments = "Generator version: 7.9.0")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

	private UUID id;

	private String name;
	private String email;
	//private String password; //por seguridad no expongo el password

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime created;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime modified;
	
	@JsonProperty("last_login")
	private LocalDateTime lastLogin;

	private String token;

	private Boolean isactive;

	private List<PhoneResponse> phones;
	 
}
