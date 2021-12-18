package pl.distributed.library.service;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.distributed.library.dto.AuthorDto;
import pl.distributed.library.dto.AuthorUpdateDto;
import pl.distributed.library.entity.Author;
import pl.distributed.library.repository.AuthorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @PersistenceContext
    private EntityManager entityManager;

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

    @Transactional
    public Author updateAuthor(AuthorUpdateDto authorUpdateDto) {
        Optional<Author> authorOpt = authorRepository.findById(authorUpdateDto.getAuthorId());
        Author author = authorOpt.get();
        Session session = (Session) entityManager.unwrap(Session.class);
        session.close();
        author.getAuthorAssignments();
        author.setForename(authorUpdateDto.getForename());
        author.setSurname(authorUpdateDto.getSurname());
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow();
        authorRepository.delete(author);
    }

    @Transactional
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }
}
