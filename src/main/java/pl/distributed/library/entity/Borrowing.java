package pl.distributed.library.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Document("borrowing")
@NoArgsConstructor
public class Borrowing {
    @Id
    private String id;
    @NotNull
    @JsonFormat
    private LocalDate validFrom;
    @NotNull
    @JsonFormat
    private LocalDate validTo;
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