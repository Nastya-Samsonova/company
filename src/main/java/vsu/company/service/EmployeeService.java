package vsu.company.service;

import org.springframework.stereotype.Service;
import vsu.company.entity.Employee;
import vsu.company.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public void updateEmployee(Long id, Employee newEmployee) {
        employeeRepository.findById(id)
                .map(employee -> {
                    employee.setFullName(newEmployee.getFullName());
                    employee.setAge(newEmployee.getAge());
                    employee.setSalary(newEmployee.getSalary());
                    return employeeRepository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
}

