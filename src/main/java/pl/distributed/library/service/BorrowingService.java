package pl.distributed.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.distributed.library.dto.*;
import pl.distributed.library.entity.*;
import pl.distributed.library.exception.BookIsNotAvailableException;
import pl.distributed.library.exception.LibraryNotFoundException;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.BorrowingMapper;
import pl.distributed.library.mapper.ClientMapper;
import pl.distributed.library.mapper.EmployeeMapper;
import pl.distributed.library.repository.BookRepository;
import pl.distributed.library.repository.BorrowingRepository;
import pl.distributed.library.repository.ClientRepository;
import pl.distributed.library.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BorrowingService {
    private BorrowingRepository borrowingRepository;
    private BookRepository bookRepository;
    private ClientRepository clientRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public BorrowingService(BorrowingRepository borrowingRepository,
                            BookRepository bookRepository,
                            ClientRepository clientRepository,
                            EmployeeRepository employeeRepository) {
        this.borrowingRepository = borrowingRepository;
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
    }

    public Optional<Borrowing> findById(Long id) {
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
        Client client = clientRepository.findById(borrowingCreateDto.getClientId())
                .orElseThrow(ResourceNotFoundException::new);
        Employee employee = employeeRepository.findById(borrowingCreateDto.getEmployeeId())
                .orElseThrow(ResourceNotFoundException::new);

        if (!book.isAvailability()) {
            throw new BookIsNotAvailableException();
        }

        Borrowing borrowing = new Borrowing();
        borrowing.setValidFrom(borrowingCreateDto.getValidFrom());
        borrowing.setValidTo(borrowingCreateDto.getValidTo());
        borrowing.setReturnDate(borrowingCreateDto.getReturnDate());
        borrowing.setBook(book);
        borrowing.setClient(client);
        borrowing.setEmployee(employee);
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
