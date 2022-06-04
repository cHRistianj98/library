package pl.distributed.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.distributed.library.entity.Customer;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    <S extends Customer> S save(S entity);

    List<Customer> findAll();

    Optional<Customer> findById(Long aLong);

    void deleteById(Long aLong);

}
