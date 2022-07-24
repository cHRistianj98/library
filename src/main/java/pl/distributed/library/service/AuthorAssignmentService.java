package pl.distributed.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.distributed.library.dto.*;
import pl.distributed.library.entity.*;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.AuthorAssignmentMapper;
import pl.distributed.library.repository.AuthorAssignmentRepository;
import pl.distributed.library.repository.AuthorRepository;
import pl.distributed.library.repository.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorAssignmentService {
    private final AuthorAssignmentRepository authorAssignmentRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorAssignmentService(AuthorAssignmentRepository authorAssignmentRepository,
                                   BookRepository bookRepository,
                                   AuthorRepository authorRepository) {
        this.authorAssignmentRepository = authorAssignmentRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Optional<AuthorAssignment> findById(Long id) {
        return authorAssignmentRepository.findById(id);
    }

    public List<AuthorAssignmentDto> findAll() {
        List<AuthorAssignment> authorAssignments = authorAssignmentRepository.findAll();
        return authorAssignments.stream()
                .map(AuthorAssignmentMapper::authorAssignmentToAuthorAssignmentDto)
                .collect(Collectors.toList());
    }

    public AuthorAssignmentDto addAuthorAssignment(AuthorAssignmentCreateDto authorAssignmentCreateDto) {
        Optional<Book> book = bookRepository.findById(authorAssignmentCreateDto.getBookId());
        Optional<Author> author = authorRepository.findById(authorAssignmentCreateDto.getAuthorId());

        if (book.isEmpty() || author.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        AuthorAssignment authorAssignment = new AuthorAssignment();
        authorAssignment.setAuthor(author.get());
        authorAssignment.setBook(book.get());
        AuthorAssignment authorAssignmentFromRepo = authorAssignmentRepository.save(authorAssignment);

//        saveAssignment(author.get(), authorAssignmentFromRepo);
//        saveAssignment(book.get(), authorAssignmentFromRepo);

        return AuthorAssignmentMapper.authorAssignmentToAuthorAssignmentDto(authorAssignmentFromRepo);
    }

    private void saveAssignment(Author author, AuthorAssignment authorAssignment) {
        Set<AuthorAssignment> authorAssignments = author.getAuthorAssignments();
        authorAssignments.add(authorAssignment);
        author.setAuthorAssignments(authorAssignments);
        authorRepository.save(author);
    }

    private void saveAssignment(Book book, AuthorAssignment authorAssignment) {
        Set<AuthorAssignment> bookAssignments = book.getAuthorAssignments();
        bookAssignments.add(authorAssignment);
        book.setAuthorAssignments(bookAssignments);
        bookRepository.save(book);
    }

    public Long deleteAuthorAssignment(Long id) {
        AuthorAssignment authorAssignment = authorAssignmentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        authorAssignmentRepository.delete(authorAssignment);
        return id;
    }

    public void deleteAuthorAssignments() {
        List<AuthorAssignment> authorAssignments = authorAssignmentRepository.findAll();
        authorAssignments.forEach(authorAssignmentRepository::delete);
    }
}
