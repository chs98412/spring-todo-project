package security.security.config.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String s){
        super(s);
    }
}