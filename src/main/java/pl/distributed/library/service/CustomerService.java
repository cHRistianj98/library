package pl.distributed.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.distributed.library.dto.CustomerDto;
import pl.distributed.library.dto.NewCustomerDto;
import pl.distributed.library.entity.Address;
import pl.distributed.library.entity.Customer;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.CustomerMapper;
import pl.distributed.library.repository.AddressRepository;
import pl.distributed.library.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public CustomerDto addCustomer(NewCustomerDto newCustomerDto) {
        Optional<Address> address = addressRepository.findByCityAndStreetAndNumberAndPostalCode(
                newCustomerDto.getAddressCreateDto().getCity(),
                newCustomerDto.getAddressCreateDto().getStreet(),
                newCustomerDto.getAddressCreateDto().getNumber(),
                newCustomerDto.getAddressCreateDto().getPostalCode());

        Customer customer = new Customer();

        if (address.isEmpty()) {
            Address newAddress = new Address();
            newAddress.setCity(newCustomerDto.getAddressCreateDto().getCity());
            newAddress.setStreet(newCustomerDto.getAddressCreateDto().getStreet());
            newAddress.setNumber(newCustomerDto.getAddressCreateDto().getNumber());
            newAddress.setPostalCode(newCustomerDto.getAddressCreateDto().getPostalCode());
            addressRepository.save(newAddress);
            customer.setAddress(newAddress);
        } else {
            customer.setAddress(address.get());
        }
        customer.setForename(newCustomerDto.getForename());
        customer.setSurname(newCustomerDto.getSurname());
        customer.setEmail(newCustomerDto.getEmail());
        customerRepository.save(customer);
        return CustomerMapper.customerToCustomerDto(customer);
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public List<CustomerDto> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(CustomerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    public Long deleteCustomer(Long id) {
        try {
            customerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException();
        }
        return id;
    }
}
