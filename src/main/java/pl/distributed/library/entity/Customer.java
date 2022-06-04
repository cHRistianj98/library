package pl.distributed.library.entity;

import lombok.Data;

import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Document("customer")
@NoArgsConstructor
public class Customer {
    @Id
    private String id;
    @NotNull
    private String forename;
    @NotNull
    private String surname;
    @NotNull
    @Email
    private String email;
    private Address address;
    private Set<Borrowing> borrowings;

    public Customer(@NotNull String forename, @NotNull String surname, @NotNull @Email String email,
                    Address address, Set<Borrowing> borrowings) {
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.address = address;
        this.borrowings = borrowings;
    }
}
