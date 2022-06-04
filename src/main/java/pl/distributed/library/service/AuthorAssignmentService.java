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

    public Optional<AuthorAssignment> findById(String id) {
        return authorAssignmentRepository.findById(id);
    }

    public List<AuthorAssignmentDto> findAll() {
        List<AuthorAssignment> authorAssignments = authorAssignmentRepository.findAll();
        return authorAssignments.stream()
                .map(AuthorAssignmentMapper::authorAssignmentToAuthorAssignmentDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public AuthorAssignmentDto addAuthorAssignment(AuthorAssignmentCreateDto authorAssignmentCreateDto) {
        Optional<Book> book = bookRepository.findById(authorAssignmentCreateDto.getId());
        Optional<Author> author = authorRepository.findById(authorAssignmentCreateDto.getAuthorId());

        if (book.isEmpty() || author.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        AuthorAssignment authorAssignment = new AuthorAssignment();
        authorAssignment.setAuthor(author.get());
        authorAssignment.setBook(book.get());
        AuthorAssignment authorAssignmentFromRepo = authorAssignmentRepository.save(authorAssignment);

        return AuthorAssignmentMapper.authorAssignmentToAuthorAssignmentDto(authorAssignmentFromRepo);
    }

    @Transactional
    public String deleteAuthorAssignment(String id) {
        try {
            authorAssignmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException();
        }
        return id;
    }
}
