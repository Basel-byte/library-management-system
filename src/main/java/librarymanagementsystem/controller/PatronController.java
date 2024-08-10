package librarymanagementsystem.controller;

import librarymanagementsystem.dto.PatronDto;
import librarymanagementsystem.service.PatronService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
@CrossOrigin
public class PatronController {
    public final PatronService service;

    @GetMapping("/patrons")
    public ResponseEntity<List<PatronDto>> getAllPatrons() {
        try {
            return new ResponseEntity<>(service.getAllPatrons(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/patrons/{id}")
    public ResponseEntity<PatronDto> getPatron(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.getPatron(id), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/patrons")
    public ResponseEntity<Void> addPatron(@RequestBody PatronDto patronDto) {
        try {
            service.addPatron(patronDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/patrons/{id}")
    public ResponseEntity<Void> updatePatron(@PathVariable Long id, @RequestBody PatronDto patronDto) {
        try {
            service.updatePatron(id, patronDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/patrons/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) {
        try {
            service.deletePatron(id);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
