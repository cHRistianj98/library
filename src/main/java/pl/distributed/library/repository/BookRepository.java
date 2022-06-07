package pl.distributed.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.distributed.library.entity.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    <S extends Book> S save(S entity);
}