package librarymanagementsystem.dto;

import librarymanagementsystem.utils.BorrowingDaysLimit;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@BorrowingDaysLimit.List({
    @BorrowingDaysLimit(
            field = "borrowDate",
            fieldMatch = "returnDate"
    )
})
public class BorrowingDto {
    private final Date borrowDate;
    private final Date returnDate;
}
