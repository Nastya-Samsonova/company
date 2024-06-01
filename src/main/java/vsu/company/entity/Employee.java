package vsu.company.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private int age;
    private double salary;

    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;

    public Employee(String fullName, int age, double salary) {
        this.fullName = fullName;
        this.age = age;
        this.salary = salary;
    }
}
