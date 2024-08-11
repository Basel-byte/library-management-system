package librarymanagementsystem.service;

import librarymanagementsystem.dto.BorrowingDto;
import librarymanagementsystem.model.Book;
import librarymanagementsystem.model.Borrowing;
import librarymanagementsystem.model.Patron;
import librarymanagementsystem.repository.BookRepository;
import librarymanagementsystem.repository.BorrowingRepository;
import librarymanagementsystem.repository.PatronRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.Year;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BorrowingServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private PatronRepository patronRepository;
    @Mock
    private BorrowingRepository borrowingRepository;

    @Mock
    private BorrowingService borrowingService;

    @Test
    void borrowBookByPatron() {
//        // given
//        Long bookId = 1L, patronId = 13L;
//        BorrowingDto borrowingDto = new BorrowingDto(Date.valueOf("2024-8-18"), Date.valueOf("2024-8-25"));
//        Book book = mock(Book.class);
//        Patron patron = mock(Patron.class);
//
//        given(bookRepository.findByIdWithLock(anyLong())).willReturn(Optional.of(book));
//        given(book.getNumberOfCopies()).willReturn(anyInt());// Mocking the number of copies
//        given(patronRepository.findById(anyLong())).willReturn(Optional.of(patron));
//
//        // when
//        borrowingService.borrowBookByPatron(bookId, patronId, borrowingDto);
//
//        // then
//        verify(bookRepository, times(1)).findByIdWithLock(anyLong());
//        verify(book, times(2)).getNumberOfCopies();
//        verify(patronRepository, times(1)).findById(anyLong());
//        verify(borrowingRepository, times(1)).save(any());
    }


    @Test
    void returnBookByPatron() {
    }
}