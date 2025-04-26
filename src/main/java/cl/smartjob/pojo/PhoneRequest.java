package cl.smartjob.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PhoneRequest
 */

//@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-04-23T17:09:14.426839900-05:00[America/Lima]", comments = "Generator version: 7.9.0")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRequest {

  private String number;

  private String citycode;

  private String countrycode;
  /*

  public PhoneRequest number(String number) {
    this.number = number;
    return this;
  }

 
  @Schema(name = "number", example = "1234567", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("number")
  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public PhoneRequest citycode(String citycode) {
    this.citycode = citycode;
    return this;
  }

 
  
  @Schema(name = "citycode", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("citycode")
  public String getCitycode() {
    return citycode;
  }

  public void setCitycode(String citycode) {
    this.citycode = citycode;
  }

  public PhoneRequest countrycode(String countrycode) {
    this.countrycode = countrycode;
    return this;
  }

 
  
  @Schema(name = "countrycode", example = "57", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("countrycode")
  public String getCountrycode() {
    return countrycode;
  }

  public void setCountrycode(String countrycode) {
    this.countrycode = countrycode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhoneRequest phoneRequest = (PhoneRequest) o;
    return Objects.equals(this.number, phoneRequest.number) &&
        Objects.equals(this.citycode, phoneRequest.citycode) &&
        Objects.equals(this.countrycode, phoneRequest.countrycode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, citycode, countrycode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PhoneRequest {\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    citycode: ").append(toIndentedString(citycode)).append("\n");
    sb.append("    countrycode: ").append(toIndentedString(countrycode)).append("\n");
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

