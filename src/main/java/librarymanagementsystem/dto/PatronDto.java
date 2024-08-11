package librarymanagementsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class PatronDto {
    private final Long id;
    @NotEmpty @Size(max = 150, message = "name must be less than or equals 150 chars")
    private final String name;

    @NotEmpty @Size(max = 95, message = "name must be less than or equals 95 chars")
    private final String address;

    @NotEmpty @Size(max = 20, message = "name must be less than or equals 20 chars")
    private final String mobileNumber;

    @Email
    private final String email;

    private List<Long> bookIds;
}