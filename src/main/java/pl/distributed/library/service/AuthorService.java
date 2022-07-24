package pl.distributed.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.distributed.library.dto.*;
import pl.distributed.library.entity.Author;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.AuthorMapper;
import pl.distributed.library.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDto saveAuthor(AuthorCreateDto authorDto) {
        Author author = new Author();
        author.setForename(authorDto.getForename());
        author.setSurname(authorDto.getSurname());
        Author authorFromRepo = authorRepository.save(author);
        return AuthorMapper.authorToAuthorDto(authorFromRepo);
    }

    public Long deleteAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(ResourceNotFoundException::new);
        authorRepository.delete(author);
        return authorId;
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
