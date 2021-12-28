package pl.distributed.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.distributed.library.dto.AddressDto;
import pl.distributed.library.dto.EmployeeCreateDto;
import pl.distributed.library.dto.EmployeeDto;
import pl.distributed.library.dto.LibraryDto;
import pl.distributed.library.entity.Address;
import pl.distributed.library.entity.Employee;
import pl.distributed.library.entity.Library;
import pl.distributed.library.exception.LibraryAlreadyExistsException;
import pl.distributed.library.exception.LibraryNotFoundException;
import pl.distributed.library.mapper.EmployeeMapper;
import pl.distributed.library.mapper.LibraryMapper;
import pl.distributed.library.repository.EmployeeRepository;
import pl.distributed.library.repository.LibraryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private LibraryRepository libraryRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, LibraryRepository libraryRepository) {
        this.employeeRepository = employeeRepository;
        this.libraryRepository = libraryRepository;
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<EmployeeDto> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(EmployeeMapper::employeeToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public EmployeeDto addEmployee(EmployeeCreateDto employeeCreateDto) {
        Optional<Library> library = libraryRepository.findById(employeeCreateDto.getLibraryId());
        if (library.isEmpty()) {
            throw new LibraryNotFoundException();
        }

        Employee employee = new Employee();
        employee.setForename(employeeCreateDto.getForename());
        employee.setSurname(employeeCreateDto.getSurname());
        employee.setEmail(employeeCreateDto.getEmail());
        employee.setSalary(employeeCreateDto.getSalary());
        employee.setLibrary(library.get());
        Employee employeeFromRepo = employeeRepository.save(employee);
        return EmployeeMapper.employeeToEmployeeDto(employeeFromRepo);
    }

    public Long deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
        return employeeId;
    }

    public Long deleteLibrary(Long id) {
        libraryRepository.deleteById(id);
        return id;
    }
}
