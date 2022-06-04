package pl.distributed.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.distributed.library.entity.Borrowing;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowingRepository extends MongoRepository<Borrowing, String> {
    <S extends Borrowing> S save(S entity);

    List<Borrowing> findAll();

    Optional<Borrowing> findById(Long aLong);

    void deleteById(Long aLong);
}
