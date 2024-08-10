package librarymanagementsystem.controller;

import librarymanagementsystem.dto.BorrowingDto;
import librarymanagementsystem.service.BorrowingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
@CrossOrigin
public class BorrowingController {
    public final BorrowingService service;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<Void> borrowBookByPatron(@PathVariable Long bookId, @PathVariable Long patronId,
                                                   @RequestBody BorrowingDto borrowingDto) {
        try {
            service.borrowBookByPatron(bookId, patronId, borrowingDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingDto> getBorrowingRecord(@PathVariable Long bookId, @PathVariable Long patronId) {
        try {
            return new ResponseEntity<>(service.getBorrowingRecord(bookId, patronId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
