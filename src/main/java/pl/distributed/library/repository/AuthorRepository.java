package pl.distributed.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.distributed.library.entity.Author;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

    <S extends Author> S save(S entity);

    List<Author> findAll();

    Optional<Author> findByForenameAndSurname(String forename, String surname);

    void delete(Author entity);
}
