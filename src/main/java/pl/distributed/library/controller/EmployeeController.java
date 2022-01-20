package pl.distributed.library.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.distributed.library.dto.AddressDto;
import pl.distributed.library.dto.EmployeeCreateDto;
import pl.distributed.library.dto.EmployeeDto;
import pl.distributed.library.dto.LibraryDto;
import pl.distributed.library.entity.Employee;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.EmployeeMapper;
import pl.distributed.library.service.EmployeeService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ApiOperation("Get employee")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = EmployeeDto.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isEmpty()) {
            throw new ResourceNotFoundException();
        } else {
            return ResponseEntity.ok(EmployeeMapper.employeeToEmployeeDto(employee.get()));
        }
    }


    @GetMapping
    @ApiOperation("Get employees")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = EmployeeDto.class)
    })
    public ResponseEntity<List<EmployeeDto>> getLibraries() {
        return ResponseEntity.ok(employeeService.findAll());
    }


    @PostMapping
    @ApiOperation("Add employee")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = LibraryDto.class)
    })
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody @Valid EmployeeCreateDto employeeCreateDto) {
        EmployeeDto employeeDto = employeeService.addEmployee(employeeCreateDto);
        return ResponseEntity.created(URI.create("/" + employeeDto.getEmployeeId())).body(employeeDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete employee")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful deleted", response = Long.class)
    })
    public ResponseEntity<Long> deleteEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.deleteEmployee(id));
    }
}
