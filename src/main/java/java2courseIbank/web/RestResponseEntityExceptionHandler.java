package java2courseIbank.web;

import java2courseIbank.AppException;
import java2courseIbank.web.DTOs.AppExceptionDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {AppException.class})
    protected ResponseEntity<Object> handleApplicationException(AppException ex,
                                                                WebRequest request) {
        AppExceptionDTO appExDTO = new AppExceptionDTO(ex.getErrors());
        return handleExceptionInternal(ex, appExDTO,
                new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleRuntimeException(AppException ex,
                                                            WebRequest request) {
        return handleExceptionInternal(ex, ex,
                new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }
}
