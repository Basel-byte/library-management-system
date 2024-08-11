package librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "patron")
public class Patron {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "patron", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Borrowing> borrowList;

    public Patron(String name, String address, String mobileNumber, String email) {
        this.name = name;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }
}
