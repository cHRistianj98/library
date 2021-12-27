package pl.distributed.library.mapper;

import pl.distributed.library.dto.BorrowingDto;
import pl.distributed.library.entity.Borrowing;

import java.util.Set;
import java.util.stream.Collectors;

public class BorrowingMapper {
    public static Set<BorrowingDto> borrowingSetToBorrowingDtoSet(Set<Borrowing> borrowings) {
        return borrowings.stream()
                .map(BorrowingMapper::borrowingToBorrowingDto)
                .collect(Collectors.toSet());
    }

    public static BorrowingDto borrowingToBorrowingDto(Borrowing borrowing) {
        BorrowingDto borrowingDto = new BorrowingDto();
        borrowingDto.setBorrowingId(borrowing.getBorrowingId());
        borrowingDto.setValidFrom(borrowing.getValidFrom());
        borrowingDto.setValidTo(borrowing.getValidTo());
        borrowingDto.setReturnDate(borrowing.getReturnDate());
        borrowingDto.setBook(BookMapper.bookToBookDto(borrowing.getBook()));
        borrowingDto.setClient(ClientMapper.clientToClientDto(borrowing.getClient()));
        borrowingDto.setEmployee(EmployeeMapper.employeeToEmployeeDto(borrowing.getEmployee()));
        return borrowingDto;
    }
}
