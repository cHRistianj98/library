package pl.distributed.library.mapper;

import pl.distributed.library.dto.BorrowingDto;
import pl.distributed.library.entity.Borrowing;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class BorrowingMapper {
    public static Set<BorrowingDto> borrowingSetToBorrowingDtoSet(Set<Borrowing> borrowings) {
        if (Objects.isNull(borrowings)) {
            return Collections.emptySet();
        }

        return borrowings.stream()
                .map(BorrowingMapper::borrowingToBorrowingDto)
                .collect(Collectors.toSet());
    }

    public static BorrowingDto borrowingToBorrowingDto(Borrowing borrowing) {
        BorrowingDto borrowingDto = new BorrowingDto();
        borrowingDto.setBorrowingId(borrowing.getId());
        borrowingDto.setValidFrom(borrowing.getValidFrom());
        borrowingDto.setValidTo(borrowing.getValidTo());
        borrowingDto.setReturnDate(borrowing.getReturnDate());
        borrowingDto.setLibraryId(borrowing.getLibrary().getId());
        borrowingDto.setBook(BookMapper.bookToBookBorrowingDto(borrowing.getBook()));
        borrowingDto.setCustomer(CustomerMapper.customerToCustomerBorrowingDto(borrowing.getCustomer()));
        return borrowingDto;
    }
}
