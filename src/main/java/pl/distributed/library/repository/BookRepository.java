package pl.distributed.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.distributed.library.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    <S extends Book> S save(S entity);
}
