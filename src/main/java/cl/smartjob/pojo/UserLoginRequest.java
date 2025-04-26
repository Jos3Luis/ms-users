package cl.smartjob.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserLoginRequest
 */

//@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-23T17:09:14.426839900-05:00[America/Lima]", comments = "Generator version: 7.9.0")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {

  private String email;

  private String password;

  public UserLoginRequest email(String email) {
    this.email = email;
    return this;
  }

  /* 
  
  @Schema(name = "email", example = "juan@rodriguez.org", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UserLoginRequest password(String password) {
    this.password = password;
    return this;
  }

 
  
  @Schema(name = "password", example = "P@ssword", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserLoginRequest userLoginRequest = (UserLoginRequest) o;
    return Objects.equals(this.email, userLoginRequest.email) &&
        Objects.equals(this.password, userLoginRequest.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserLoginRequest {\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("}");
    return sb.toString();
  }

   
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }*/
}

