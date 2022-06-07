package pl.distributed.library.mapper;

import pl.distributed.library.dto.BookBorrowingDto;
import pl.distributed.library.dto.BookDto;
import pl.distributed.library.entity.AuthorAssignment;
import pl.distributed.library.entity.Book;

import java.util.Collections;
import java.util.Objects;
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
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setReleaseYear(book.getReleaseYear());
        bookDto.setDescription(book.getDescription());
        bookDto.setAvailability(book.isAvailability());
        if (Objects.nonNull(book.getBorrowings())) {
            bookDto.setBorrowings(book.getBorrowings().stream()
                .map(BorrowingMapper::borrowingToBorrowingBookDto)
                .collect(Collectors.toList()));
        } else {
            bookDto.setBorrowings(Collections.emptyList());
        }

        if (Objects.nonNull(book.getAuthorAssignments())) {
            bookDto.setAuthors(book.getAuthorAssignments()
                    .stream()
                    .map(AuthorAssignment::getAuthor)
                    .map(AuthorMapper::authorToAuthorUpdateDto)
                    .collect(Collectors.toList()));
        } else {
            bookDto.setAuthors(Collections.emptyList());
        }
        return bookDto;
    }

    public static BookBorrowingDto bookToBookBorrowingDto(Book book) {
        BookBorrowingDto bookBorrowingDto = new BookBorrowingDto();
        bookBorrowingDto.setId(book.getId());
        bookBorrowingDto.setTitle(book.getTitle());
        bookBorrowingDto.setReleaseYear(book.getReleaseYear());
        bookBorrowingDto.setDescription(book.getDescription());
        bookBorrowingDto.setAvailability(book.isAvailability());
        if (Objects.nonNull(book.getAuthorAssignments())) {
            bookBorrowingDto.setAuthors(book.getAuthorAssignments()
                    .stream()
                    .map(AuthorAssignment::getAuthor)
                    .map(AuthorMapper::authorToAuthorUpdateDto)
                    .collect(Collectors.toList()));
        } else {
            bookBorrowingDto.setAuthors(Collections.emptyList());
        }
        return bookBorrowingDto;
    }
}
