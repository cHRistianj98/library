package pl.distributed.library.mapper;

import pl.distributed.library.dto.CustomerDto;
import pl.distributed.library.entity.Customer;

import java.util.Set;
import java.util.stream.Collectors;

public class CustomerMapper {
    public static Set<CustomerDto> clientSetToClientDtoSet(Set<Customer> customers) {
        return customers.stream()
                .map(CustomerMapper::clientToClientDto)
                .collect(Collectors.toSet());
    }

    public static CustomerDto clientToClientDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setClientId(customer.getId());
        customerDto.setEmail(customer.getEmail());
        customerDto.setForename(customer.getForename());
        customerDto.setSurname(customer.getSurname());
        return customerDto;
    }
}
