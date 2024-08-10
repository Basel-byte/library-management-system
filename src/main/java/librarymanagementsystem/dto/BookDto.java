package librarymanagementsystem.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.Year;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class BookDto {
    @NotEmpty
    private final String title;
    @NotEmpty
    private final String author;

    private final int publicationYear;
    private final String isbn;
    private final int numberOfCopies;
    private List<Long> patronIds;
}