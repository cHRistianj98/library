package pl.distributed.library.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    @NotNull
    @Column(name = "forename", nullable = false)
    private String forename;

    @NotNull
    @Column(name = "surname", nullable = false)
    private String surname;

    @NotNull
    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "debt")
    private int debt;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "client")
    private Set<Borrowing> borrowings;

    public Client(@NotNull String forename, @NotNull String surname, @NotNull @Email String email,
                  int debt, Address address, Set<Borrowing> borrowings) {
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.debt = debt;
        this.address = address;
        this.borrowings = borrowings;
    }
}
