package pl.distributed.library.mapper;

import pl.distributed.library.dto.EmployeeDto;
import pl.distributed.library.dto.LibraryDto;
import pl.distributed.library.entity.Employee;
import pl.distributed.library.entity.Library;

import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeMapper {
    public static Set<EmployeeDto> employeeSetToEmployeeDtoSet(Set<Employee> employees) {
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
        employeeDto.setBorrowings(BorrowingMapper.borrowingSetToBorrowingDtoSet(employee.getBorrowings()));
        employeeDto.setLibrary(LibraryMapper.libraryToLibraryDto(employee.getLibrary()));
        return employeeDto;
    }
}
