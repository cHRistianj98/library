//package pl.distributed.library.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import pl.distributed.library.dto.*;
//import pl.distributed.library.entity.*;
//import pl.distributed.library.exception.ResourceNotFoundException;
//import pl.distributed.library.mapper.AddressMapper;
//import pl.distributed.library.mapper.BookMapper;
//import pl.distributed.library.mapper.LibraryMapper;
//import pl.distributed.library.repository.AuthorAssignmentRepository;
//import pl.distributed.library.repository.AuthorRepository;
//import pl.distributed.library.repository.BookRepository;
//import pl.distributed.library.repository.BorrowingRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class BookService {
//    private final BookRepository bookRepository;
//    private final AuthorRepository authorRepository;
//    private final AuthorAssignmentRepository authorAssignmentRepository;
//    private BorrowingRepository borrowingRepository;
//
//    @Autowired
//    public BookService(BookRepository bookRepository, AuthorRepository authorRepository,
//                       AuthorAssignmentRepository authorAssignmentRepository, BorrowingRepository borrowingRepository) {
//        this.bookRepository = bookRepository;
//        this.authorRepository = authorRepository;
//        this.authorAssignmentRepository = authorAssignmentRepository;
//        this.borrowingRepository = borrowingRepository;
//    }
//
//    public BookDto findById(Long id) {
//        Book book = bookRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
//        return BookMapper.bookToBookDto(book);
//    }
//
//    public List<BookDto> findAll() {
//        List<Book> books = bookRepository.findAll();
//        return books.stream()
//                .map(BookMapper::bookToBookDto)
//                .collect(Collectors.toList());
//    }
//
//    public List<BookDto> findBooksByCustomerId(Long id) {
//        List<Borrowing> borrowings = borrowingRepository.findAll();
//
//        return borrowings.stream()
//                .filter(borrowing -> borrowing.getCustomer().getId().equals(id))
//                .map(Borrowing::getBook)
//                .map(BookMapper::bookToBookDto)
//                .collect(Collectors.toList());
//    }
//
//    @Transactional
//    public BookDto addBook(BookCreateDto bookCreateDto) {
//        Book book = new Book();
//        book.setAvailability(true);
//        book.setTitle(bookCreateDto.getTitle());
//        book.setDescription(bookCreateDto.getDescription());
//        book.setReleaseYear(bookCreateDto.getReleaseYear());
//        Book bookFromRepo = bookRepository.save(book);
//
//        List<Long> authorIds = createAuthors(bookCreateDto.getAuthors());
//        createAuthorAssignment(bookFromRepo.getId(), authorIds);
//
//        return BookMapper.bookToBookDto(bookFromRepo);
//    }
//
//    @Transactional
//    public BookDto updateBook(BookUpdateDto bookUpdateDto) {
//        Book book = bookRepository.findById(bookUpdateDto.getId())
//                .orElseThrow(ResourceNotFoundException::new);
//        book.setTitle(bookUpdateDto.getTitle());
//        book.setDescription(bookUpdateDto.getDescription());
//        book.setReleaseYear(bookUpdateDto.getReleaseYear());
//        book.setAvailability(bookUpdateDto.isAvailability());
//        Book bookFromRepo = bookRepository.save(book);
//        return BookMapper.bookToBookDto(bookFromRepo);
//    }
//
//    @Transactional
//    public Long deleteBook(Long id) {
//        try {
//            bookRepository.deleteById(id);
//        } catch (Exception ex) {
//            throw new ResourceNotFoundException();
//        }
//        return id;
//    }
//
//    private List<Long> createAuthors(List<AuthorCreateDto> authorCreateDto) {
//        List<Long> authorIds = new ArrayList<>();
//        for (AuthorCreateDto createDto : authorCreateDto) {
//            Optional<Author> author = authorRepository.findByForenameAndSurname(
//                    createDto.getForename(),
//                    createDto.getSurname());
//            if (author.isEmpty()) {
//                Author newAuthor = new Author();
//                newAuthor.setForename(createDto.getForename());
//                newAuthor.setSurname(createDto.getSurname());
//                Author authorFromRepo = authorRepository.save(newAuthor);
//                authorIds.add(authorFromRepo.getId());
//            } else {
//                authorIds.add(author.get().getId());
//            }
//        }
//        return authorIds;
//    }
//
//    private void createAuthorAssignment(Long bookId, List<Long> authorIds) {
//        Optional<Book> book = bookRepository.findById(bookId);
//        if (book.isEmpty()) {
//            throw new ResourceNotFoundException();
//        }
//
//        for (int i=0; i<authorIds.size(); i++) {
//            Optional<Author> author = authorRepository.findById(authorIds.get(i));
//            if (author.isEmpty()) {
//                throw new ResourceNotFoundException();
//            }
//
//            AuthorAssignment authorAssignment = new AuthorAssignment();
//            authorAssignment.setBook(book.get());
//            authorAssignment.setAuthor(author.get());
//            authorAssignmentRepository.save(authorAssignment);
//        }
//    }
//}
