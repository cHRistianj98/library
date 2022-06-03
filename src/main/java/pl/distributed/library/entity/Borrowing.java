package pl.distributed.library.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "borrowing")
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "valid_from")
    @JsonFormat
    private LocalDate validFrom;

    @NotNull
    @Column(name = "valid_to")
    @JsonFormat
    private LocalDate validTo;

    @Column(name = "return_date")
    @JsonFormat
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "library_id", nullable = false)
    private Library library;

    public Borrowing(@NotNull LocalDate validFrom, @NotNull LocalDate validTo, LocalDate returnDate, Book book,
                     Customer customer, Library library) {
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.returnDate = returnDate;
        this.book = book;
        this.customer = customer;
        this.library = library;
    }
}
