package pl.distributed.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.distributed.library.entity.AuthorAssignment;

import java.util.List;

@Repository
public interface AuthorAssignmentRepository extends CrudRepository<AuthorAssignment, Long> {

    <S extends AuthorAssignment> S save(S entity);

    List<AuthorAssignment> findAll();

    void delete(AuthorAssignment entity);
}
