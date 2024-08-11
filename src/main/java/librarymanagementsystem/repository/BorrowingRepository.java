package librarymanagementsystem.repository;

import jakarta.persistence.LockModeType;
import librarymanagementsystem.model.Book;
import librarymanagementsystem.model.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    Optional<Borrowing> getBorrowingByBook_IdAndPatron_Id(Long book_id, Long patron_id);
    Optional<Void> deleteBorrowingByBook_IdAndPatron_Id(Long book_id, Long patron_id);

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM Borrowing b WHERE b.book.id = :bookId AND b.patron.id =:patronId")
    void deleteByBookIdAndPatronId(Long bookId, Long patronId);
}
