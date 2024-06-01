package vsu.company.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Employee> employees;

    public Department(String name) {
        this.name = name;
    }

    public int getEmployeeCount() {
        return employees.size();
    }

    public double getTotalSalary() {
        return employees.stream().mapToDouble(Employee::getSalary).sum();
    }
}
