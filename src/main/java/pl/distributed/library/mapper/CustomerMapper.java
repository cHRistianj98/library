package pl.distributed.library.mapper;

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
        return customerDto;
    }
}
