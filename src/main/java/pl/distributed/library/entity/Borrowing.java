package pl.distributed.library.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private Book book;
    private Customer customer;
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
