package vsu.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vsu.company.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}