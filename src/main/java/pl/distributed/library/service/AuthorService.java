package pl.distributed.library.service;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.distributed.library.dto.*;
import pl.distributed.library.entity.Address;
import pl.distributed.library.entity.Author;
import pl.distributed.library.entity.Library;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.AddressMapper;
import pl.distributed.library.mapper.AuthorMapper;
import pl.distributed.library.mapper.LibraryMapper;
import pl.distributed.library.repository.AuthorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public AuthorDto saveAuthor(AuthorCreateDto authorDto) {
        Author author = new Author();
        author.setForename(authorDto.getForename());
        author.setSurname(authorDto.getSurname());
        Author authorFromRepo = authorRepository.save(author);
        return AuthorMapper.authorToAuthorDto(authorFromRepo);
    }

    public void deleteAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow();
        authorRepository.delete(author);
    }

    public AuthorDto updateAuthor(AuthorUpdateDto authorUpdateDto) {
        Author author = authorRepository.findById(authorUpdateDto.getId())
                .orElseThrow(ResourceNotFoundException::new);
        author.setForename(authorUpdateDto.getForename());
        author.setSurname(authorUpdateDto.getSurname());
        Author authorFromRepo = authorRepository.save(author);
        return AuthorMapper.authorToAuthorDto(authorFromRepo);
    }


    public AuthorDto findById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return AuthorMapper.authorToAuthorDto(author);
    }

    public List<AuthorDto> findAll() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(AuthorMapper::authorToAuthorDto)
                .collect(Collectors.toList());
    }
}
