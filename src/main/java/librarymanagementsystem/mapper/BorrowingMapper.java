package librarymanagementsystem.mapper;

import librarymanagementsystem.dto.BorrowingDto;
import librarymanagementsystem.model.Borrowing;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BorrowingMapper {
    BorrowingDto borrowingToBorrowingDto(Borrowing borrowing);
}
