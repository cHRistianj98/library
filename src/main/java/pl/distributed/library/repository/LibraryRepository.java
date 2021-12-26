package pl.distributed.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.distributed.library.entity.Library;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

    <S extends Library> S save(S entity);

    void deleteById(Long aLong);

    List<Library> findAll();
}
