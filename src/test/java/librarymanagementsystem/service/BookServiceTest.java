package librarymanagementsystem.service;

import librarymanagementsystem.dto.BookDto;
import librarymanagementsystem.mapper.BookMapper;
import librarymanagementsystem.model.Book;
import librarymanagementsystem.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository repository;
    @Mock
    private BookMapper mapper;

    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService(repository, mapper);
    }

    @Test
    void getAllBooks() {
        // given
        List<Book> books = List.of(
                new Book("Cracking the Coding Interview", "Gayle Laakmann McDowell", Year.of(2008), "9780984782871",15)
        );
        List<BookDto> bookDtos = List.of(
                new BookDto(0L, "Cracking the Coding Interview", "Gayle Laakmann McDowell", 2008, "9780984782871",10)
        );
        given(repository.findAll()).willReturn(books);
        given(mapper.booksToBookDtos(books)).willReturn(bookDtos);

        // when
        List<BookDto> bookDtoList = bookService.getAllBooks();

        // then
        verify(repository, times(1)).findAll();
        verify(mapper, times(1)).booksToBookDtos(books);
        assertThat(bookDtoList).isEqualTo(bookDtos);
    }

    @Test
    void getBook() {
        // given
        Long id = 1L;
        Optional<Book> optionalBook = Optional.of(
                new Book("Cracking the Coding Interview", "Gayle Laakmann McDowell", Year.of(2008), "9780984782871",10)
        );
        BookDto bookDto = new BookDto(0L, "Cracking the Coding Interview", "Gayle Laakmann McDowell", 2008, "9780984782871",10);
        given(repository.findById(id)).willReturn(optionalBook);
        given(mapper.bookToBookDto(optionalBook.get())).willReturn(bookDto);

        // when
        BookDto returnedBookDto = bookService.getBook(id);

        // then
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(repository, times(1)).findById(longArgumentCaptor.capture());
        verify(mapper, times(1)).bookToBookDto(optionalBook.get());
        assertThat(returnedBookDto).isEqualTo(bookDto);
        assertThat(longArgumentCaptor.getValue()).isEqualTo(id);
    }

    @Test
    void addBook() {
        // given
        Book book = new Book("Cracking the Coding Interview", "Gayle Laakmann McDowell", Year.of(2008), "9780984782871",10);
        BookDto bookDto = new BookDto(0L, "Cracking the Coding Interview", "Gayle Laakmann McDowell", 2008, "9780984782871",10);
        given(mapper.bookDtoToBook(bookDto)).willReturn(book);
        given(repository.save(book)).willReturn(any());

        // when
        bookService.addBook(bookDto);

        // then
        ArgumentCaptor<BookDto> argumentCaptor = ArgumentCaptor.forClass(BookDto.class);
        verify(mapper, times(1)).bookDtoToBook(argumentCaptor.capture());
        verify(repository, times(1)).save(book);
        assertThat(argumentCaptor.getValue()).isEqualTo(bookDto);
    }

    @Test
    void updateBook() {
        // given
        Long id = 1L;
        Book book = new Book("Cracking the Coding Interview", "Gayle Laakmann McDowell", Year.of(2008), "9780984782871",10);
        BookDto bookDto = new BookDto(0L, "Cracking the Coding Interview", "Gayle Laakmann McDowell", 2008, "9780984782871",10);
        given(repository.findByIdWithLock(id)).willReturn(Optional.of(book));
        given(repository.save(book)).willReturn(any());

        // when
        bookService.updateBook(id, bookDto);

        // then
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<BookDto> bookDtoArgumentCaptor = ArgumentCaptor.forClass(BookDto.class);
        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(repository, times(1)).findByIdWithLock(longArgumentCaptor.capture());
        verify(mapper, times(1)).updateBookFromBookDto(bookDtoArgumentCaptor.capture(), bookArgumentCaptor.capture());
        verify(repository, times(1)).save(book);
        assertThat(bookDtoArgumentCaptor.getValue()).isEqualTo(bookDto);
        assertThat(longArgumentCaptor.getValue()).isEqualTo(id);
    }

    @Test
    void deleteBook() {
        // given
        Long id = 1L;
        Book book = new Book("Cracking the Coding Interview", "Gayle Laakmann McDowell", Year.of(2008), "9780984782871",10);
        given(repository.findByIdWithLock(id)).willReturn(Optional.of(book));

        // when
        bookService.deleteBook(id);

        // then
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(repository, times(1)).findByIdWithLock(longArgumentCaptor.capture());
        verify(repository, times(1)).delete(book);
        assertThat(longArgumentCaptor.getValue()).isEqualTo(id);
    }
}