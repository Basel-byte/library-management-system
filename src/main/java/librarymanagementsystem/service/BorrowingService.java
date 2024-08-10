package librarymanagementsystem.service;

import librarymanagementsystem.dto.BorrowingDto;
import librarymanagementsystem.mapper.BorrowingMapper;
import librarymanagementsystem.model.Book;
import librarymanagementsystem.model.Borrowing;
import librarymanagementsystem.model.Patron;
import librarymanagementsystem.repository.BookRepository;
import librarymanagementsystem.repository.BorrowingRepository;
import librarymanagementsystem.repository.PatronRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BorrowingService {
    private final BorrowingRepository borrowingRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    private final BorrowingMapper borrowingMapper;


    public void borrowBookByPatron(Long bookId, Long patronId, BorrowingDto borrowingDto) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        if (book.getNumberOfCopies() == 0)
            throw new UnsupportedOperationException("This book is out of stock!");
        book.setNumberOfCopies(book.getNumberOfCopies() - 1);

        Patron patron = patronRepository.findById(patronId).orElseThrow();

        Borrowing borrowing = new Borrowing(borrowingDto.getBorrowDate(), borrowingDto.getReturnDate(), book, patron);
        book.getBorrowList().add(borrowing);
        patron.getBorrowList().add(borrowing);

        bookRepository.save(book);
        patronRepository.save(patron);
        borrowingRepository.save(borrowing);
    }

    public BorrowingDto getBorrowingRecord(Long bookId, Long patronId) {
        Borrowing borrowing = borrowingRepository
                .getBorrowingByBook_IdAndPatron_Id(bookId, patronId).orElseThrow();
        return borrowingMapper.borrowingToBorrowingDto(borrowing);
    }
}
