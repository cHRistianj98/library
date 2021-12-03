package pl.distributed.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private int clientId;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

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
    
    @OneToMany(mappedBy = "client")
    private Set<Address> addresses;

    public Client() {
    }

    public Client(int clientId, @NotNull String name, @NotNull String forename, @NotNull String surname,
                  @NotNull @Email String email, int debt, Set<Address> addresses) {
        this.clientId = clientId;
        this.name = name;
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.debt = debt;
        this.addresses = addresses;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int id) {
        this.clientId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", debt=" + debt +
                ", addresses=" + addresses +
                '}';
    }
}
