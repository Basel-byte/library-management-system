package librarymanagementsystem.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class PatronDto {
    private final String name;
    private final String address;
    private final String mobileNumber;
    private List<Long> bookIds;
}