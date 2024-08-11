package librarymanagementsystem.service;

import jakarta.persistence.EntityNotFoundException;
import librarymanagementsystem.dto.BorrowingDto;
import librarymanagementsystem.model.Book;
import librarymanagementsystem.model.Borrowing;
import librarymanagementsystem.model.Patron;
import librarymanagementsystem.repository.BookRepository;
import librarymanagementsystem.repository.BorrowingRepository;
import librarymanagementsystem.repository.PatronRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class BorrowingService {
    private final BorrowingRepository borrowingRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    @Transactional
    public void borrowBookByPatron(Long bookId, Long patronId, BorrowingDto borrowingDto) {
        Book book = bookRepository.findByIdWithLock(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found!"));
        if (book.getNumberOfCopies() == 0)
            throw new UnsupportedOperationException("This book is out of stock!");
        book.setNumberOfCopies(book.getNumberOfCopies() - 1);

        Patron patron = patronRepository.findById(patronId).orElseThrow(() -> new EntityNotFoundException("Patron not found!"));

        Borrowing borrowing = new Borrowing(borrowingDto.getBorrowDate(), borrowingDto.getReturnDate(), book, patron);

        borrowingRepository.save(borrowing);
    }

    @Transactional
    public void returnBookByPatron(Long bookId, Long patronId) {
//        borrowingRepository.deleteByBook_IdAndPatron_Id(bookId, patronId)
//                .orElseThrow(() -> new EntityNotFoundException("Book or patron not found!"));
        borrowingRepository.deleteByBookIdAndPatronId(bookId, patronId);
        Book book = bookRepository.findByIdWithLock(bookId).orElseThrow();
        book.setNumberOfCopies(book.getNumberOfCopies() + 1);
    }
}
