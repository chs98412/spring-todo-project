package security.security.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BadRequestException.class)
    public String BadRequestException(BadRequestException e){
        String msg = e.getMessage();
        int status = 400;
        return "1";
    }

    @ExceptionHandler(MethodNotAllowException.class)
    public String MethodNotAllowException(MethodNotAllowException e){
        String msg = e.getMessage();
        int status = 403;
        return "1";
    }


    @ExceptionHandler(NotFoundException.class)
    public String NotFoundException(NotFoundException e){
        String msg = e.getMessage();
        int status = 404;
        return "1";
    }

    @ExceptionHandler(GlobalException.class)
    public String GlobalException(GlobalException e){
        String msg = e.getMessage();
        int status = 500;
        return "1";
    }
}
