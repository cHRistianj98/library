package pl.distributed.library.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.distributed.library.entity.*;
import pl.distributed.library.repository.AuthorAssignmentRepository;
import pl.distributed.library.repository.AuthorRepository;
import pl.distributed.library.repository.BookRepository;

import java.util.HashSet;

@Service
public class StartService {
    private static Logger logger = LoggerFactory.getLogger(StartService.class);
    private AuthorRepository authorRepository;
    private AuthorAssignmentRepository authorAssignmentRepository;
    private BookRepository bookRepository;

    @Autowired
    public StartService(AuthorRepository authorRepository, BookRepository bookRepository,
                        AuthorAssignmentRepository authorAssignmentRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.authorAssignmentRepository = authorAssignmentRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDatabase() {
        Author author = new Author("Robert", "Martin", new HashSet<>());
        Book book = new Book("Czysty kod", "Podręcznik dobrego programisty", 2010, true, new HashSet<>(), new HashSet<>());

        authorRepository.save(author);
        logger.info("Authors were added!");

        bookRepository.save(book);
        logger.info("Books were added!");

        authorAssignmentRepository.save(new AuthorAssignment(author, book));
        logger.info("Author assignments were added!");

        //Address address = new Address();

        //Library library = new Library(1, );
        logger.info("library was added!");
    }
}
