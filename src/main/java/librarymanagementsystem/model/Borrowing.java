package librarymanagementsystem.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Date;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "borrowing", uniqueConstraints = @UniqueConstraint(name = "book_patron_id", columnNames = {"book_id", "patron_id"}))
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "borrow_date", nullable = false)
    private Date borrowDate;

    @Column(name = "return_date", nullable = false)
    private Date returnDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patron_id")
    private Patron patron;

    public Borrowing(Date borrowDate, Date returnDate, Book book, Patron patron) {
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.book = book;
        this.patron = patron;
    }
}
