package librarymanagementsystem.controller;

import jakarta.validation.Valid;
import librarymanagementsystem.dto.PatronDto;
import librarymanagementsystem.service.PatronService;
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
public class PatronController {
    public final PatronService service;

    @GetMapping("/patrons")
    public ResponseEntity<?> getAllPatrons() {
        return new ResponseEntity<>(service.getAllPatrons(), HttpStatus.OK);
    }

    @GetMapping("/patrons/{id}")
    public ResponseEntity<?> getPatron(@PathVariable Long id) {
        return new ResponseEntity<>(service.getPatron(id), HttpStatus.OK);
    }

    @PostMapping("/patrons")
    public ResponseEntity<String> addPatron(@Valid @RequestBody PatronDto patronDto) {
        service.addPatron(patronDto);
        return new ResponseEntity<>("Patron added successfully ✅", HttpStatus.CREATED);
    }

    @PutMapping("/patrons/{id}")
    public ResponseEntity<String> updatePatron(@PathVariable Long id, @Valid @RequestBody PatronDto patronDto) {
        service.updatePatron(id, patronDto);
        return new ResponseEntity<>("Patron updated successfully ✅", HttpStatus.OK);
    }

    @DeleteMapping("/patrons/{id}")
    public ResponseEntity<String> deletePatron(@PathVariable Long id) {
        service.deletePatron(id);
        return new ResponseEntity<>("Patron deleted successfully ✅", HttpStatus.OK);
    }
}
