package vsu.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vsu.company.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}

