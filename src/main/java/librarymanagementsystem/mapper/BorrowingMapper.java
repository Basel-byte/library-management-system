package librarymanagementsystem.mapper;

import librarymanagementsystem.dto.BorrowingDto;
import librarymanagementsystem.model.Borrowing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BorrowingMapper {

    Borrowing borrowingDtoToBorrowing(BorrowingDto borrowingDto);
    BorrowingDto borrowingToBorrowingDto(Borrowing borrowing);
}
