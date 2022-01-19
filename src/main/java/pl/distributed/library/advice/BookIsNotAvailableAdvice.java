package pl.distributed.library.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.distributed.library.exception.BookIsNotAvailableException;

@ControllerAdvice
public class BookIsNotAvailableAdvice {
    @ResponseBody
    @ExceptionHandler(BookIsNotAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String resourceNotFoundHandler(BookIsNotAvailableException ex) {
        return ex.getMessage();
    }
}
