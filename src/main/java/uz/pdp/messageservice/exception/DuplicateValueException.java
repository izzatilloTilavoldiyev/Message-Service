package uz.pdp.messageservice.exception;

public class DuplicateValueException extends RuntimeException{
    public DuplicateValueException(String message) {
        super(message);
    }
}
