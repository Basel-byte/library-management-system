package librarymanagementsystem.repository;

import librarymanagementsystem.model.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    Optional<Borrowing> getBorrowingByBook_IdAndPatron_Id(Long book_id, Long patron_id);
}
