package pl.distributed.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;

    @NotNull
    @Column(name = "salary", nullable = false)
    private int salary;

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

    @ManyToOne
    @JoinColumn(name = "library_id", nullable = false)
    private Library library;

    public Employee() {
    }

    public Employee(int employeeId, @NotNull int salary, @NotNull String forename, @NotNull String surname,
                    @NotNull @Email String email, Library library) {
        this.employeeId = employeeId;
        this.salary = salary;
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.library = library;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
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

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", salary=" + salary +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", library=" + library +
                '}';
    }
}
