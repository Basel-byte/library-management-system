package librarymanagementsystem.mapper;

import librarymanagementsystem.dto.PatronDto;
import librarymanagementsystem.model.Borrowing;
import librarymanagementsystem.model.Patron;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PatronMapper {
//    PatronMapper INSTANCE = Mappers.getMapper(PatronMapper.class);

    Patron patronDtoToPatron(PatronDto patronDto);

    @Mapping(source = "borrowList", target = "bookIds", qualifiedByName = "listOfBorrowingToListOfLong")
    PatronDto patronToPatronDto(Patron patron);

    @Named("listOfBorrowingToListOfLong")
    default List<Long> listOfBorrowingToListOfLong(List<Borrowing> borrowList) {
        return borrowList.stream().map(b -> b.getBook().getId()).toList();
    }

    List<PatronDto> patronsToPatronDtos(List<Patron> patrons);

    void updatePatronFromPatronDto(PatronDto patronDto, @MappingTarget Patron patron);
}
