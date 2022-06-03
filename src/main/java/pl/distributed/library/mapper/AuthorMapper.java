package pl.distributed.library.mapper;

import pl.distributed.library.dto.AuthorDto;
import pl.distributed.library.entity.Author;

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
        return authorDto;
    }
}
