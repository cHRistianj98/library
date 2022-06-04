package pl.distributed.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.distributed.library.entity.Address;

import java.util.Optional;

@Repository
public interface AddressRepository extends MongoRepository<Address, String> {

    Optional<Address> findByCityAndStreetAndNumberAndPostalCode(String city,
                                                                String street,
                                                                String number,
                                                                String postalCode);

    <S extends Address> S save(S entity);
}
