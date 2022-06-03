package pl.distributed.library.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.distributed.library.dto.CustomerDto;
import pl.distributed.library.dto.NewCustomerDto;
import pl.distributed.library.entity.Customer;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.CustomerMapper;
import pl.distributed.library.service.CustomerService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation("Get customer")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = CustomerDto.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getLibrary(@PathVariable Long id) {
        Optional<Customer> client = customerService.findById(id);
        if (client.isEmpty()) {
            throw new ResourceNotFoundException();
        } else {
            return ResponseEntity.ok(CustomerMapper.clientToClientDto(client.get()));
        }
    }

    @ApiOperation("Get customers")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = CustomerDto.class)
    })
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getLibraries() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @ApiOperation("Add customer")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = CustomerDto.class)
    })
    @PostMapping
    public ResponseEntity<CustomerDto> addClient(@RequestBody NewCustomerDto newCustomerDto) {
        CustomerDto customerDto = customerService.addCustomer(newCustomerDto);
        return ResponseEntity.created(URI.create("/" + customerDto.getClientId())).body(customerDto);
    }

    @ApiOperation("Delete customer")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = Long.class)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteClient(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.deleteClient(id));
    }
}
