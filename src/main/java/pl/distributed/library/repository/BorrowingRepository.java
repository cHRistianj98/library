//package pl.distributed.library.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import pl.distributed.library.entity.Borrowing;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
//    <S extends Borrowing> S save(S entity);
//
//    List<Borrowing> findAll();
//
//    Optional<Borrowing> findById(Long aLong);
//
//    void deleteById(Long aLong);
//}
