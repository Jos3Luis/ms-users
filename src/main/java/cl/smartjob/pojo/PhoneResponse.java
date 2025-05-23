package cl.smartjob.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneResponse {

  private String number;

  private String citycode;

  private String countrycode;
  
}

