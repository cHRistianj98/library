package pl.distributed.library.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.distributed.library.dto.ClientDto;
import pl.distributed.library.dto.EmployeeDto;
import pl.distributed.library.entity.Client;
import pl.distributed.library.entity.Employee;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.ClientMapper;
import pl.distributed.library.mapper.EmployeeMapper;
import pl.distributed.library.service.EmployeeService;

import java.util.Optional;

@RestController
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
}
