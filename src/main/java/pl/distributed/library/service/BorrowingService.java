package pl.distributed.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.distributed.library.dto.*;
import pl.distributed.library.entity.*;
import pl.distributed.library.exception.BookIsNotAvailableException;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.BorrowingMapper;
import pl.distributed.library.repository.BookRepository;
import pl.distributed.library.repository.BorrowingRepository;
import pl.distributed.library.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BorrowingService {
    private final BorrowingRepository borrowingRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public BorrowingService(BorrowingRepository borrowingRepository,
                            BookRepository bookRepository,
                            CustomerRepository customerRepository) {
        this.borrowingRepository = borrowingRepository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    public Optional<Borrowing> findById(String id) {
        return borrowingRepository.findById(id);
    }

    public List<BorrowingDto> findAll() {
        List<Borrowing> borrowings = borrowingRepository.findAll();
        return borrowings.stream()
                .map(BorrowingMapper::borrowingToBorrowingDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public BorrowingDto addBorrowing(BorrowingCreateDto borrowingCreateDto) {
        Book book = bookRepository.findById(borrowingCreateDto.getBookId())
                .orElseThrow(ResourceNotFoundException::new);
        Customer customer = customerRepository.findById(borrowingCreateDto.getId())
                .orElseThrow(ResourceNotFoundException::new);

        if (!book.isAvailability()) {
            throw new BookIsNotAvailableException();
        }

        Borrowing borrowing = new Borrowing();
        borrowing.setValidFrom(borrowingCreateDto.getValidFrom());
        borrowing.setValidTo(borrowingCreateDto.getValidTo());
        borrowing.setReturnDate(borrowingCreateDto.getReturnDate());
        borrowing.setBook(book);
        borrowing.setCustomer(customer);
        Borrowing borrowingFromRepo = borrowingRepository.save(borrowing);
        book.setAvailability(false);
        return BorrowingMapper.borrowingToBorrowingDto(borrowingFromRepo);
    }

    @Transactional
    public Long deleteBorrowing(Long id) {
        try {
            borrowingRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException();
        }
        return id;
    }
}
