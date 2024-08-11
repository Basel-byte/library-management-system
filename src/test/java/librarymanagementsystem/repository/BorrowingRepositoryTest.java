package librarymanagementsystem.repository;

import librarymanagementsystem.model.Book;
import librarymanagementsystem.model.Borrowing;
import librarymanagementsystem.model.Patron;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.time.Year;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BorrowingRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PatronRepository patronRepository;
    @Autowired
    private BorrowingRepository borrowingRepository;

    @BeforeEach
    void setUp()  {
        Book book1 = new Book("Cracking the Coding Interview", "Gayle Laakmann McDowell", Year.of(2008), "9780984782871",10);
        Book book2 = new Book("Oliver Twist", "Charles Dickens", Year.of(1838), "9780192815910",15);
        bookRepository.saveAll(List.of(book1, book2));

        Patron patron1 = new Patron("Basel Ahmed Awad", "11 Bikingham Street, Alexandria", "+201097453125", "basel@gmail.com");
        Patron patron2 = new Patron("Mohamed Magdy El Sayed", "16 Notingham Street, Alexandria", "+201097453125", "magdy@gmail.com");
        patronRepository.saveAll(List.of(patron1, patron2));

        borrowingRepository.save(
                new Borrowing(Date.valueOf("2024-08-7"), Date.valueOf("2024-08-15"), book1, patron1)
        );
    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
        patronRepository.deleteAll();
        borrowingRepository.deleteAll();
    }

    @Test
    @Order(1)
    void borrowBookByPatronSuccessfully() {
        Optional<Book> book = bookRepository.findBookByTitle("Oliver Twist");
        assertThat(book).isPresent();
        Optional<Patron> patron = patronRepository.findPatronByEmail("basel@gmail.com");
        assertThat(patron).isPresent();
        borrowingRepository.save(
                new Borrowing(Date.valueOf("2024-08-11"), Date.valueOf("2024-08-16"), book.get(), patron.get())
        );
        assertThat(borrowingRepository.findAll().isEmpty()).isFalse();
    }

    @Test
    @Order(2)
    void getBorrowRecordSuccessfully() {
        Optional<Book> book = bookRepository.findBookByTitle("Cracking the Coding Interview");
        assertThat(book).isPresent();
        Optional<Patron> patron = patronRepository.findPatronByEmail("magdy@gmail.com");
        assertThat(patron).isPresent();
        borrowingRepository.save(
                new Borrowing(Date.valueOf("2024-08-11"), Date.valueOf("2024-08-16"), book.get(), patron.get())
        );
        Borrowing expected = new Borrowing(Date.valueOf("2024-08-11"), Date.valueOf("2024-08-16"), book.get(), patron.get());
        System.out.println(borrowingRepository.findAll().size());
        Long bookId = book.get().getId(), patronId = patron.get().getId();
        Optional<Borrowing> actual = borrowingRepository.getBorrowingByBook_IdAndPatron_Id(bookId, patronId);
        assertThat(actual).isNotEmpty();
        assertThat(actual.get().getBorrowDate()).isEqualTo(expected.getBorrowDate());
        assertThat(actual.get().getReturnDate()).isEqualTo(expected.getReturnDate());
    }

    @Test
    void returnBookByPatronSuccessfully() {
        Optional<Book> book = bookRepository.findBookByTitle("Cracking the Coding Interview");
        assertThat(book).isPresent();
        Optional<Patron> patron = patronRepository.findPatronByEmail("basel@gmail.com");
        assertThat(patron).isPresent();
        Long bookId = book.get().getId(), patronId = patron.get().getId();
        borrowingRepository.deleteBorrowingByBook_IdAndPatron_Id(bookId, patronId);

        Assertions.assertThrows(NoSuchElementException.class,
                () -> borrowingRepository.getBorrowingByBook_IdAndPatron_Id(bookId, patronId).orElseThrow());
    }

    @Test
    void returnBookByPatronFail() {
        Optional<Book> book = bookRepository.findBookByTitle("Cracking the Coding Interview");
        assertThat(book).isPresent();
        Optional<Patron> patron = patronRepository.findPatronByEmail("magdy@gmail.com");
        assertThat(patron).isPresent();
        Long bookId = book.get().getId(), patronId = patron.get().getId();
        int noOfRows = borrowingRepository.findAll().size();
        borrowingRepository.deleteBorrowingByBook_IdAndPatron_Id(bookId, patronId);
        assertThat(noOfRows).isEqualTo(borrowingRepository.findAll().size());
        Assertions.assertThrows(NoSuchElementException.class,
                () -> borrowingRepository.getBorrowingByBook_IdAndPatron_Id(bookId, patronId).orElseThrow());
    }
}