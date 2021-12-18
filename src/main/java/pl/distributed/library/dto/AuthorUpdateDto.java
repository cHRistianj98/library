package pl.distributed.library.dto;

import javax.validation.constraints.NotNull;

public class AuthorUpdateDto {
    @NotNull
    private Long authorId;

    @NotNull
    private String forename;

    @NotNull
    private String surname;

    public AuthorUpdateDto(@NotNull Long authorId, @NotNull String forename, @NotNull String surname) {
        this.authorId = authorId;
        this.forename = forename;
        this.surname = surname;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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
        return "AuthorUpdateDto{" +
                "authorId=" + authorId +
                ", forename='" + forename + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
