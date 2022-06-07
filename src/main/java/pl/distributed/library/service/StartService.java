package pl.distributed.library.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.distributed.library.entity.*;
import pl.distributed.library.repository.*;

import java.util.HashSet;

@Service
public class StartService {
    private static final Logger logger = LoggerFactory.getLogger(StartService.class);
    private final AuthorRepository authorRepository;
    private final AuthorAssignmentRepository authorAssignmentRepository;
    private final BookRepository bookRepository;
    private final AddressRepository addressRepository;
    private final LibraryRepository libraryRepository;
    private final CustomerRepository customerRepository;
    private final BorrowingRepository borrowingRepository;

    @Autowired
    public StartService(AuthorRepository authorRepository, AuthorAssignmentRepository authorAssignmentRepository,
                        BookRepository bookRepository, AddressRepository addressRepository,
                        LibraryRepository libraryRepository, CustomerRepository customerRepository,
                        BorrowingRepository borrowingRepository) {
        this.authorRepository = authorRepository;
        this.authorAssignmentRepository = authorAssignmentRepository;
        this.bookRepository = bookRepository;
        this.addressRepository = addressRepository;
        this.libraryRepository = libraryRepository;
        this.customerRepository = customerRepository;
        this.borrowingRepository = borrowingRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDatabase() {
        Author author = new Author("Robert", "Martin", new HashSet<>());
        Book book = new Book("Czysty kod", "Podręcznik dobrego programisty",
                2010, true, new HashSet<>(), new HashSet<>());

        authorRepository.save(author);
        logger.info("Author was added!");

        bookRepository.save(book);
        logger.info("Book was added!");

        authorAssignmentRepository.save(new AuthorAssignment(author, book));
        logger.info("Author assignments were added!");

        Address libraryAddress = new Address("Wrocław", "Jana Pawła 2", "1", "52-222");
        Address customerAddress = new Address("Toruń", "Januszowa", "4", "88-800");
        addressRepository.save(customerAddress);

        Library library = new Library(libraryAddress);
        libraryRepository.save(library);
        logger.info("Library was added!");

        Customer customer = new Customer("Marcin", "Nowak", "marcinnowak@wp.pl",
                customerAddress, new HashSet<>());
        customerRepository.save(customer);
        logger.info("Customer was added!");
//
//        Borrowing borrowing = new Borrowing(LocalDate.now(), LocalDate.now().plusMonths(1), LocalDate.now().plusDays(1), book, client, employee);
//        borrowingRepository.save(borrowing);
//        logger.info("borrowing was added!");
    }
}
