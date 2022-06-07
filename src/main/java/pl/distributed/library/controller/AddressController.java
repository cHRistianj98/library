package pl.distributed.library.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.distributed.library.dto.*;
import pl.distributed.library.entity.Address;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.AddressMapper;
import pl.distributed.library.service.AddressService;

import javax.validation.Valid;
import java.net.URI;
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
        return ResponseEntity.ok(addressService.findById(id));
    }

    @GetMapping
    @ApiOperation("Get addresses")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = AddressDto.class)
    })
    public ResponseEntity<List<AddressDto>> getAddresses() {
        return ResponseEntity.ok(addressService.findAll());
    }


    @ApiOperation("Add address")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = AddressDto.class)
    })
    @PostMapping
    public ResponseEntity<AddressDto> addAddress(@RequestBody @Valid AddressCreateDto addressCreateDto) {
        AddressDto addressDto = addressService.addAddress(addressCreateDto);
        return ResponseEntity.created(URI.create("/" + addressDto.getAddressId())).body(addressDto);
    }

    @ApiOperation("Update address")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful updated", response = AddressDto.class)
    })
    @PutMapping
    public ResponseEntity<AddressDto> updateAddress(@RequestBody @Valid AddressUpdateDto addressUpdateDto) {
        return ResponseEntity.ok(addressService.updateAddress(addressUpdateDto));
    }

    @ApiOperation("Delete address")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = Long.class)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteAddress(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.deleteAddress(id));
    }
}
