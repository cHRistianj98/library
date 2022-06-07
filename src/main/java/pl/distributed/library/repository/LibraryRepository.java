package pl.distributed.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.distributed.library.entity.Address;
import pl.distributed.library.entity.Library;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryRepository extends MongoRepository<Library, String> {

    <S extends Library> S save(S entity);

    void deleteById(String aLong);

    List<Library> findAll();

    Optional<Library> findById(Long aLong);

    Optional<Library> findByAddress(Address address);
}