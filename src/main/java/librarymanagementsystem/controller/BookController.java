package librarymanagementsystem.controller;

import jakarta.validation.Valid;
import librarymanagementsystem.dto.BookDto;
import librarymanagementsystem.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin()
@Validated
public class BookController {
    private final BookService service;

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks() {
        return new ResponseEntity<>(service.getAllBooks(), HttpStatus.OK);
    }


    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id) {
        return new ResponseEntity<>(service.getBook(id), HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<String> addBook(@Valid @RequestBody BookDto bookDto) {
        service.addBook(bookDto);
        return new ResponseEntity<>("Book added successfully ✅", HttpStatus.CREATED);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @Valid @RequestBody BookDto bookDto) {
        service.updateBook(id, bookDto);
        return new ResponseEntity<>("Book updated successfully ✅", HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
        return new ResponseEntity<>("Book deleted successfully ✅", HttpStatus.OK);
    }
}
