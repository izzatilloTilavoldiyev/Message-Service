package uz.pdp.messageservice.handlers;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.pdp.messageservice.entity.ErrorMessage;
import uz.pdp.messageservice.exception.DataNotFoundException;
import uz.pdp.messageservice.exception.DuplicateValueException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({DataNotFoundException.class})
    public ResponseEntity<ErrorMessage> dataNotFoundException(RuntimeException e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(404).body(message);
    }

    @ExceptionHandler({DuplicateValueException.class})
    public ResponseEntity<ErrorMessage> duplicateValueException(RuntimeException e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT, e.getMessage());
        return ResponseEntity.status(409).body(message);
    }

    @ExceptionHandler({SizeLimitExceededException.class})
    public ResponseEntity<ErrorMessage> sizeLimitExceptionHandler(SizeLimitExceededException e) {
        ErrorMessage message = new ErrorMessage(HttpStatus.LENGTH_REQUIRED, e.getMessage());
        return ResponseEntity.status(411).body(message);
    }

}
