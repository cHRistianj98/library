package pl.distributed.library.exception;

public class LibraryNotFoundException extends RuntimeException {
    public LibraryNotFoundException() {
        super("Library not found with this id!");
    }
}
