package pl.distributed.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.distributed.library.entity.Address;
import pl.distributed.library.entity.Library;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

    <S extends Library> S save(S entity);

    void deleteById(Long aLong);

    List<Library> findAll();

    Optional<Library> findById(Long aLong);

    Optional<Library> findByAddress(Address address);
}
