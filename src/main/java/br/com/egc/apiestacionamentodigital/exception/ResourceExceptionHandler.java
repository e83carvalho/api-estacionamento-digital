package br.com.egc.apiestacionamentodigital.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static java.util.Optional.ofNullable;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<Object> handleConflict(BusinessException ex, WebRequest request) {
        HttpHeaders responseHeaders = new HttpHeaders();

        return ResponseEntity.status(ex.getHttpStatusCode()).headers(responseHeaders).body(ex.getOnlyBody());

    }

    @ExceptionHandler({Throwable.class})
    public ResponseEntity<Object> handleException(Throwable eThrowable) {

        BusinessException ex = BusinessException.builder()
                .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ofNullable(eThrowable.getMessage()).orElse(eThrowable.toString()))
                .description(eThrowable.getMessage())
                .build();
        HttpHeaders responseHeaders = new HttpHeaders();

        return ResponseEntity.status(ex.getHttpStatusCode()).headers(responseHeaders).body(ex.getOnlyBody());

    }


    @ExceptionHandler({ResourceValidatorException.class})
    public ResponseEntity<Object> handleResourceValidatorException(ResourceValidatorException resourceValidatorException, WebRequest request) {

        BusinessException ex = BusinessException.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST)
                .message(resourceValidatorException.getMessage())
                .description(resourceValidatorException.getMessage())
                .build();
        var responseHeaders = new HttpHeaders();


        return ResponseEntity.status(ex.getHttpStatusCode()).headers(responseHeaders).body(ex.getOnlyBody());
    }


    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleException(ResourceNotFoundException resourceNotFoundException) {
        BusinessException ex = BusinessException.builder()
                .httpStatusCode(HttpStatus.NOT_FOUND)
                .message(resourceNotFoundException.getMessage())
                .description(resourceNotFoundException.getMessage())
                .build();
        var responseHeaders = new HttpHeaders();

        return ResponseEntity.status(ex.getHttpStatusCode()).headers(responseHeaders).body(ex.getOnlyBody());
    }


}
