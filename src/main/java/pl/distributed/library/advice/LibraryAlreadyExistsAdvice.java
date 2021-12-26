package pl.distributed.library.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.distributed.library.exception.LibraryAlreadyExistsException;

@ControllerAdvice
public class LibraryAlreadyExistsAdvice {
    @ResponseBody
    @ExceptionHandler(LibraryAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String libraryAlreadyExistsHandler(LibraryAlreadyExistsException ex) {
        return ex.getMessage();
    }
}
