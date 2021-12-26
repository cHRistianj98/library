package pl.distributed.library.exception;

public class LibraryAlreadyExistsException extends RuntimeException {
    public LibraryAlreadyExistsException() {
        super("Library already exists!");
    }
}
