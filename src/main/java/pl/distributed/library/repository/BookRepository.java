package pl.distributed.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.distributed.library.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    <S extends Book> S save(S entity);
}
