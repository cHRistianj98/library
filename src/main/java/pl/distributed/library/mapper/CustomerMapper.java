package pl.distributed.library.mapper;

import pl.distributed.library.dto.CustomerBorrowingDto;
import pl.distributed.library.dto.CustomerDto;
import pl.distributed.library.entity.Customer;

import java.util.Set;
import java.util.stream.Collectors;

public class CustomerMapper {
    public static Set<CustomerDto> customerSetToCustomerDtoSet(Set<Customer> customers) {
        return customers.stream()
                .map(CustomerMapper::customerToCustomerDto)
                .collect(Collectors.toSet());
    }

    public static CustomerDto customerToCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setEmail(customer.getEmail());
        customerDto.setForename(customer.getForename());
        customerDto.setSurname(customer.getSurname());
        customerDto.setAddress(AddressMapper.addressToAddressCreateDto(customer.getAddress()));
        return customerDto;
    }

    public static CustomerBorrowingDto customerToCustomerBorrowingDto(Customer customer) {
        CustomerBorrowingDto customerBorrowingDto = new CustomerBorrowingDto();
        customerBorrowingDto.setId(customer.getId());
        customerBorrowingDto.setEmail(customer.getEmail());
        customerBorrowingDto.setForename(customer.getForename());
        customerBorrowingDto.setSurname(customer.getSurname());
        customerBorrowingDto.setAddress(AddressMapper.addressToAddressCreateDto(customer.getAddress()));
        return customerBorrowingDto;
    }
}
