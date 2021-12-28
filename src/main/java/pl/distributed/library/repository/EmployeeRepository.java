package pl.distributed.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.distributed.library.entity.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    <S extends Employee> S save(S entity);

    List<Employee> findAll();

    Optional<Employee> findById(Long aLong);

    void deleteById(Long aLong);
}
