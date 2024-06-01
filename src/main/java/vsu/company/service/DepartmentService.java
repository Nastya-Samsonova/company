package vsu.company.service;

import org.springframework.stereotype.Service;
import vsu.company.entity.Department;
import vsu.company.repository.DepartmentRepository;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void addDepartment(Department department) {
        departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    public void updateDepartment(Long id, Department newDepartment) {
        departmentRepository.findById(id)
                .map(department -> {
                    department.setName(newDepartment.getName());
                    return departmentRepository.save(department);
                })
                .orElseGet(() -> {
                    newDepartment.setId(id);
                    return departmentRepository.save(newDepartment);
                });
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartment(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }
}
