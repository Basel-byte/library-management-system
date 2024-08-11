package librarymanagementsystem.mapper;

import librarymanagementsystem.dto.BookDto;
import librarymanagementsystem.model.Book;
import librarymanagementsystem.model.Borrowing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "publicationYear", expression = "java(java.time.Year.of(bookDto.getPublicationYear()))")
    Book bookDtoToBook(BookDto bookDto);

    @Mapping(target = "publicationYear", expression = "java(book.getPublicationYear().getValue())")
    @Mapping(source = "borrowList", target = "patronIds", qualifiedByName = "listOfBorrowingToListOfLong")
    BookDto bookToBookDto(Book book);

    @Named("listOfBorrowingToListOfLong")
    default List<Long> listOfBorrowingToListOfLong(List<Borrowing> borrowList) {
        return borrowList.stream().map(b -> b.getPatron().getId()).toList();
    }

    List<BookDto> booksToBookDtos(List<Book> books);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "publicationYear", expression = "java(java.time.Year.of(bookDto.getPublicationYear()))")
    void updateBookFromBookDto(BookDto bookDto, @MappingTarget Book book);
}
