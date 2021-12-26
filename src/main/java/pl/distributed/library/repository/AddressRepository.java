package pl.distributed.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.distributed.library.entity.Address;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByCityAndStreetAndNumberAndPostalCode(String city,
                                                                String street,
                                                                String number,
                                                                String postalCode);

    <S extends Address> S save(S entity);
}
