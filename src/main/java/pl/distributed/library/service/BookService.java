package pl.distributed.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.distributed.library.dto.BookDto;
import pl.distributed.library.dto.BorrowingDto;
import pl.distributed.library.entity.Book;
import pl.distributed.library.entity.Borrowing;
import pl.distributed.library.mapper.BookMapper;
import pl.distributed.library.mapper.BorrowingMapper;
import pl.distributed.library.repository.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(BookMapper::bookToBookDto)
                .collect(Collectors.toList());
    }
}
