package pl.distributed.library.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.distributed.library.entity.Author;
import pl.distributed.library.entity.Book;
import pl.distributed.library.repository.AuthorRepository;
import pl.distributed.library.repository.BookRepository;

import java.util.HashSet;

@Service
public class StartService {
    private static Logger logger = LoggerFactory.getLogger(StartService.class);
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public StartService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDatabase() {
        authorRepository.save(new Author("Robert", "Martin", new HashSet<>()));
        authorRepository.save(new Author("Marcin", "Najman", new HashSet<>()));
        authorRepository.save(new Author("Robert", "Makłowicz", new HashSet<>()));
        authorRepository.save(new Author("Andrzej", "Duda", new HashSet<>()));
        authorRepository.save(new Author("Jerzy", "Kryszak", new HashSet<>()));
        logger.info("Authors were added!");

        bookRepository.save(new Book("Czysty kod", "Podręcznik dobrego programisty", 2010, true, new HashSet<>(), new HashSet<>()));
        logger.info("Books were added!");

    }
}
