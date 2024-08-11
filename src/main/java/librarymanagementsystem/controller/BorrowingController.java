package librarymanagementsystem.controller;

import jakarta.validation.Valid;
import librarymanagementsystem.dto.BorrowingDto;
import librarymanagementsystem.service.BorrowingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
@CrossOrigin
@Validated
public class BorrowingController {
    public final BorrowingService service;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<String> borrowBookByPatron(@PathVariable Long bookId, @PathVariable Long patronId,
                                               @Valid @RequestBody BorrowingDto borrowingDto) {
        service.borrowBookByPatron(bookId, patronId, borrowingDto);
        return new ResponseEntity<>("Borrowing procedure is done successfully ✅", HttpStatus.CREATED);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<String> returnBookByPatron(@PathVariable Long bookId, @PathVariable Long patronId) {
        service.returnBookByPatron(bookId, patronId);
        return new ResponseEntity<>("Book is returned successfully to the library ✅", HttpStatus.CREATED);
    }
}
