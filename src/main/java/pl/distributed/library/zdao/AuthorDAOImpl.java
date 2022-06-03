package pl.distributed.library.zdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.distributed.library.zmodel.Author;
import pl.distributed.library.zmodel.AuthorMapper;

import java.util.List;

@Component
public class AuthorDAOImpl implements AuthorDAO{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Author> getAllAuthors() {
        return jdbcTemplate.query("SELECT * FROM author", new AuthorMapper());
    }

    @Override
    public Author findByAuthorId(Long id) {
        Author author = null;
        try {
            author = jdbcTemplate.queryForObject(
                    "SELECT * FROM author where author_id = ?", new Object[] {id}, new AuthorMapper());
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
        return author;
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        return null;
    }

    @Override
    public Author addAuthor(Author author) {
        jdbcTemplate.update("INSERT INTO author (author_id, forename, surname) VALUES (?,?,?)",
                author.getAuthorId(),
                author.getForename(),
                author.getSurname());
        return findByAuthorId(author.getAuthorId());
    }

    @Override
    public boolean deleteAuthor(Long id) {
        return false;
    }

}
