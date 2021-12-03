package pl.distributed.library.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

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

@Entity
@Table(name = "borrowing")
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrowing_id")
    private int borrowingId;

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

    public Borrowing() {
    }

    public Borrowing(int borrowingId, @NotNull LocalDate validFrom, @NotNull LocalDate validTo,
                     LocalDate returnDate, Book book, Client client, Employee employee) {
        this.borrowingId = borrowingId;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.returnDate = returnDate;
        this.book = book;
        this.client = client;
        this.employee = employee;
    }

    public int getBorrowingId() {
        return borrowingId;
    }

    public void setBorrowingId(int borrowingId) {
        this.borrowingId = borrowingId;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Borrowing{" +
                "borrowingId=" + borrowingId +
                ", validFrom=" + validFrom +
                ", validTo=" + validTo +
                ", returnDate=" + returnDate +
                ", book=" + book +
                ", client=" + client +
                ", employee=" + employee +
                '}';
    }
}
