package pl.distributed.library.mapper;

import pl.distributed.library.dto.BookDto;
import pl.distributed.library.entity.Book;

import java.util.Set;
import java.util.stream.Collectors;

public class BookMapper {
    public static Set<BookDto> bookSetToBookDtoSet(Set<Book> books) {
        return books.stream()
                .map(BookMapper::bookToBookDto)
                .collect(Collectors.toSet());
    }

    public static BookDto bookToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setBookId(book.getBookId());
        bookDto.setTitle(book.getTitle());
        bookDto.setReleaseYear(book.getReleaseYear());
        bookDto.setDescription(book.getDescription());
        bookDto.setAvailability(book.isAvailability());
        return bookDto;
    }
}
