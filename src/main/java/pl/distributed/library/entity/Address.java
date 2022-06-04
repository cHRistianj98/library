package pl.distributed.library.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Document("address")
@NoArgsConstructor
public class Address {
    @Id
    private String id;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private String number;

    @NotNull
    private String postalCode;

    private Set<Customer> customers;

    private Library library;

    public Address(@NotNull String city, @NotNull String street, @NotNull String number, @NotNull String postalCode) {
        this.city = city;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
    }
}
