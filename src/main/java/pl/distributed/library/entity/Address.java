package pl.distributed.library.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @NotNull
    @Column(name = "city", nullable = false)
    private String city;

    @NotNull
    @Column(name = "street", nullable = false)
    private String street;

    @NotNull
    @Column(name = "number", nullable = false)
    private String number;

    @NotNull
    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
    private Set<Client> clients;

    @OneToOne(mappedBy = "address")
    private Library library;

    public Address(Long addressId, @NotNull String city, @NotNull String street, @NotNull String number, @NotNull String postalCode) {
        this.addressId = addressId;
        this.city = city;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
    }
}
