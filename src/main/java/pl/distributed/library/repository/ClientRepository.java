package pl.distributed.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.distributed.library.entity.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    <S extends Client> S save(S entity);

    List<Client> findAll();

    Optional<Client> findById(Long aLong);

    void deleteById(Long aLong);

}
