package pl.distributed.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.distributed.library.dto.AuthorDto;
import pl.distributed.library.entity.Author;
import pl.distributed.library.repository.AuthorRepository;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveAuthor(AuthorDto authorDto) {
        Author author = new Author();
        author.setForename(authorDto.getForename());
        author.setSurname(authorDto.getSurname());
        return authorRepository.save(author);
    }
}
