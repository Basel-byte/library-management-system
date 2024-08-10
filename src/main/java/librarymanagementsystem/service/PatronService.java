package librarymanagementsystem.service;

import librarymanagementsystem.dto.PatronDto;
import librarymanagementsystem.mapper.PatronMapper;
import librarymanagementsystem.model.Patron;
import librarymanagementsystem.repository.PatronRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PatronService {
    private final PatronRepository repository;
    private final PatronMapper mapper;

    public List<PatronDto> getAllPatrons() {
        List<Patron> patrons = repository.findAll();
        return mapper.patronsToPatronDtos(patrons);
    }

    public PatronDto getPatron(Long id) {
        Patron patron = repository.findById(id).orElseThrow();
        return mapper.patronToPatronDto(patron);
    }

    public void addPatron(PatronDto patronDto) {
        Patron patron = mapper.patronDtoToPatron(patronDto);
        repository.save(patron);
    }

    public void updatePatron(Long id, PatronDto patronDto) {
        Patron patron = repository.findById(id).orElseThrow();
        mapper.updatePatronFromPatronDto(patronDto, patron);
        repository.save(patron);
    }

    public void deletePatron(Long id) {
        Patron patron = repository.findById(id).orElseThrow();
        repository.delete(patron);
    }
}
