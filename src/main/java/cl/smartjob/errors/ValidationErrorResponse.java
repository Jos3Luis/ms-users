package cl.smartjob.errors;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationErrorResponse {
    private int code;
    private String message;
    private List<FieldErrorDetail> errors;
}
