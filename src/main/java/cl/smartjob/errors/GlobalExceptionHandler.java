package cl.smartjob.errors;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cl.smartjob.pojo.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleUnauthorized(UnauthorizedException ex) {
        return ErrorResponse.builder()
                .code(401)
                .message(ex.getMessage())
                .build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldErrorDetail> errorDetails = ex.getBindingResult().getFieldErrors().stream()
            .map(err -> new FieldErrorDetail(err.getField(), err.getDefaultMessage()))
            .collect(Collectors.toList());

        return ValidationErrorResponse.builder()
                .code(400)
                .message("Error de validación")
                .errors(errorDetails)
                .build();
    }
}