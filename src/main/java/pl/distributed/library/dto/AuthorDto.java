package pl.distributed.library.dto;

import javax.validation.constraints.NotNull;

public class AuthorDto {
    @NotNull
    private String forename;

    @NotNull
    private String surname;


    public AuthorDto(@NotNull String forename, @NotNull String surname) {
        this.forename = forename;
        this.surname = surname;
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


    @Override
    public String toString() {
        return "AuthorDto{" +
                "forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
