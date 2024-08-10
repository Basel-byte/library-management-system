package librarymanagementsystem.dto;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.Accessors;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class BorrowingDto {
    private final Date borrowDate;
    private final Date returnDate;
}
