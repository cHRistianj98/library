package pl.distributed.library.mapper;

import pl.distributed.library.dto.EmployeeCreateDto;
import pl.distributed.library.dto.EmployeeDto;
import pl.distributed.library.entity.Employee;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeMapper {
    public static Set<EmployeeDto> employeeSetToEmployeeDtoSet(Set<Employee> employees) {
        if (Objects.isNull(employees)) {
            return Collections.emptySet();
        }

        return employees.stream()
                .map(EmployeeMapper::employeeToEmployeeDto)
                .collect(Collectors.toSet());
    }

    public static EmployeeDto employeeToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setSurname(employee.getSurname());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setEmployeeId(employee.getEmployeeId());
        employeeDto.setForename(employee.getForename());
        employeeDto.setSalary(employee.getSalary());
        return employeeDto;
    }

    public static EmployeeCreateDto employeeToEmployeeCreateDto(Employee employee) {
        EmployeeCreateDto employeeCreateDto = new EmployeeCreateDto();
        employeeCreateDto.setSurname(employee.getSurname());
        employeeCreateDto.setEmail(employee.getEmail());
        employeeCreateDto.setForename(employee.getForename());
        employeeCreateDto.setSalary(employee.getSalary());
        return employeeCreateDto;
    }
}
