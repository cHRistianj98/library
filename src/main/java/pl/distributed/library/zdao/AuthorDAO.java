package pl.distributed.library.zdao;


import pl.distributed.library.zmodel.Author;

import java.util.List;

public interface AuthorDAO {
    List<Author> getAllAuthors();

    Author findByAuthorId(Long id);

    Author updateAuthor(Long id, Author author);

    Author addAuthor(Author author);

    boolean deleteAuthor(Long id);
}
