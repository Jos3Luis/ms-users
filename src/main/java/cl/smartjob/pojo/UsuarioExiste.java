package cl.smartjob.pojo;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * UsuarioExiste
 */

//@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-23T17:09:14.426839900-05:00[America/Lima]", comments = "Generator version: 7.9.0")
public class UsuarioExiste {

  private String mensaje;

  public UsuarioExiste mensaje(String mensaje) {
    this.mensaje = mensaje;
    return this;
  }

  /**
   * Get mensaje
   * @return mensaje
   */
  
 //@Schema(name = "mensaje", example = "El correo ya se encuentra registrado", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("mensaje")
  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UsuarioExiste usuarioExiste = (UsuarioExiste) o;
    return Objects.equals(this.mensaje, usuarioExiste.mensaje);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mensaje);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UsuarioExiste {\n");
    sb.append("    mensaje: ").append(toIndentedString(mensaje)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

