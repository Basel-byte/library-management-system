package librarymanagementsystem.service;

import librarymanagementsystem.dto.BookDto;
import librarymanagementsystem.mapper.BookMapper;
import librarymanagementsystem.model.Book;
import librarymanagementsystem.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@AllArgsConstructor
@Service
public class BookService {

    private final BookRepository repository;
    private final BookMapper mapper;

    public List<BookDto> getAllBooks() {
        List<Book> books = repository.findAll();
        return mapper.booksToBookDtos(books);
    }

    public BookDto getBook(Long id) {
        Book book = repository.findById(id).orElseThrow();
        return mapper.bookToBookDto(book);
    }

    public void addBook(BookDto bookDto) {
        Book book = mapper.bookDtoToBook(bookDto);
        repository.save(book);
    }

    public void updateBook(Long id, BookDto bookDto) {
        Book book = repository.findById(id).orElseThrow();
        mapper.updateBookFromBookDto(bookDto, book);
        repository.save(book);
    }

    public void deleteBook(Long id) {
        Book book = repository.findById(id).orElseThrow();
        repository.delete(book);
    }
}
