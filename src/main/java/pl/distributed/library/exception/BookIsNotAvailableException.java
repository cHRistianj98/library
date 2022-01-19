package pl.distributed.library.exception;

public class BookIsNotAvailableException extends RuntimeException {
    public BookIsNotAvailableException() {
        super("Chosen book is not available!");
    }
}
