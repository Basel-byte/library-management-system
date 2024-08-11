package librarymanagementsystem.dto;

import jakarta.validation.constraints.*;
import librarymanagementsystem.utils.YearLimit;
import lombok.*;
import org.hibernate.validator.constraints.ISBN;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class BookDto {
    private final Long id;

    @NotEmpty @Size(max = 250, message = "title must be less than or equals 250 chars")
    private final String title;

    @NotEmpty @Size(max = 200, message = "author must be less than or equals 250 chars")
    private final String author;

    @NotNull @YearLimit()
    private final Integer publicationYear;

    @ISBN()
    private final String isbn;

    @NotNull
    @Min(value = 0, message = "numberOfCopies cannot be negative!")
    private final Integer numberOfCopies;

    private List<Long> patronIds;
}