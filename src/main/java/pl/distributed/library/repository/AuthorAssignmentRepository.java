package pl.distributed.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.distributed.library.entity.AuthorAssignment;

import java.util.List;

@Repository
public interface AuthorAssignmentRepository extends MongoRepository<AuthorAssignment, String> {

    <S extends AuthorAssignment> S save(S entity);

    List<AuthorAssignment> findAll();

    void delete(AuthorAssignment entity);
}