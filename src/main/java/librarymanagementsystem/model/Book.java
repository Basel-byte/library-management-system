package librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.ColumnDefault;

import java.time.Year;
import java.util.List;
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(name = "publication_year", nullable = false)
    private Year publicationYear;

    @Column(nullable = false)
    private String isbn;

    @ColumnDefault("0")
    @Column(name = "number_of_copies")
    private int numberOfCopies;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Borrowing> borrowList;

    public Book(String title, String author, Year publicationYear, String isbn, int numberOfCopies) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.numberOfCopies = numberOfCopies;
    }
}
