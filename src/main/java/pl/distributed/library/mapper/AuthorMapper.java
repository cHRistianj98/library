package pl.distributed.library.mapper;

import pl.distributed.library.dto.AuthorDto;
import pl.distributed.library.dto.AuthorUpdateDto;
import pl.distributed.library.entity.Author;
import pl.distributed.library.entity.AuthorAssignment;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthorMapper {
    public static Set<AuthorDto> authorSetToAuthorDtoSet(Set<Author> authors) {
        return authors.stream()
                .map(AuthorMapper::authorToAuthorDto)
                .collect(Collectors.toSet());
    }

    public static AuthorDto authorToAuthorDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setForename(author.getForename());
        authorDto.setSurname(author.getSurname());
        authorDto.setAuthorId(author.getId());
        if (Objects.nonNull(author.getAuthorAssignments())) {
            authorDto.setBooks(author.getAuthorAssignments()
                    .stream()
                    .map(AuthorAssignment::getBook)
                    .map(BookMapper::bookToBookDto)
                    .collect(Collectors.toList()));
        } else {
            authorDto.setBooks(Collections.emptyList());
        }
        return authorDto;
    }

    public static AuthorUpdateDto authorToAuthorUpdateDto(Author author) {
        AuthorUpdateDto authorUpdateDto = new AuthorUpdateDto();
        authorUpdateDto.setForename(author.getForename());
        authorUpdateDto.setSurname(author.getSurname());
        authorUpdateDto.setId(author.getId());
        return authorUpdateDto;
    }
}
