package pl.distributed.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.distributed.library.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    <S extends Book> S save(S entity);

    List<Book> findAll();

    void delete(Book entity);
}
