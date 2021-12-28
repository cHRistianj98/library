package pl.distributed.library.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.distributed.library.dto.AddressDto;
import pl.distributed.library.dto.AuthorAssignmentDto;
import pl.distributed.library.dto.BookDto;
import pl.distributed.library.entity.Address;
import pl.distributed.library.entity.AuthorAssignment;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.AddressMapper;
import pl.distributed.library.mapper.AuthorAssignmentMapper;
import pl.distributed.library.repository.AddressRepository;
import pl.distributed.library.service.AddressService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @ApiOperation("Get address")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = AddressDto.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable Long id) {
        Optional<Address> address = addressService.findById(id);
        if (address.isEmpty()) {
            throw new ResourceNotFoundException();
        } else {
            return ResponseEntity.ok(
                    AddressMapper.addressToAddressDto(address.get()));
        }
    }

    @GetMapping
    @ApiOperation("Get addresses")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = AddressDto.class)
    })
    public ResponseEntity<List<AddressDto>> getBooks() {
        return ResponseEntity.ok(addressService.findAll());
    }
}
