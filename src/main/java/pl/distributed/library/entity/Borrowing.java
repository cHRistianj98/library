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
    @Column(name = "borrowing_id")
    private Long borrowingId;

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
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public Borrowing(@NotNull LocalDate validFrom, @NotNull LocalDate validTo, LocalDate returnDate, Book book,
                     Client client, Employee employee) {
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.returnDate = returnDate;
        this.book = book;
        this.client = client;
        this.employee = employee;
    }
}
