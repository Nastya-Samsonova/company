package vsu.company.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import vsu.company.entity.Department;
import vsu.company.entity.Employee;
import vsu.company.service.DepartmentService;
import vsu.company.service.EmployeeService;

import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineApp implements CommandLineRunner {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final Scanner scanner = new Scanner(System.in);

    public CommandLineApp(ApplicationContext context) {
        this.departmentService = context.getBean(DepartmentService.class);
        this.employeeService = context.getBean(EmployeeService.class);
    }

    @Override
    public void run(String... args) {
        int action;
        do {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Добавить отдел");
            System.out.println("2. Добавить сотрудника");
            System.out.println("3. Удалить отдел");
            System.out.println("4. Удалить сотрудника");
            System.out.println("5. Редактировать отдел");
            System.out.println("6. Редактировать сотрудника");
            System.out.println("7. Показать всех сотрудников в отделе");
            System.out.println("8. Показать все отделы");
            System.out.println("9. Показать сумму зарплат в отделе");
            System.out.println("10. Выйти");
            System.out.print("Введите номер действия: ");
            action = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (action) {
                case 1:
                    addDepartment();
                    break;
                case 2:
                    addEmployee();
                    break;
                case 3:
                    deleteDepartment();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    editDepartment();
                    break;
                case 6:
                    editEmployee();
                    break;
                case 7:
                    showEmployeesInDepartment();
                    break;
                case 8:
                    showAllDepartments();
                    break;
                case 9:
                    showTotalSalaryInDepartment();
                    break;
                case 10:
                    System.out.println("Выход из программы...");
                    break;
                default:
                    System.out.println("Неверный ввод, попробуйте снова.");
            }
        } while (action != 10);
    }

    private void addDepartment() {
        System.out.print("Введите название отдела: ");
        String name = scanner.nextLine();
        Department department = new Department(name);
        departmentService.addDepartment(department);
        System.out.println("Отдел добавлен.");
    }

    private void addEmployee() {
        System.out.print("Введите ФИО сотрудника: ");
        String fullName = scanner.nextLine();
        System.out.print("Введите возраст сотрудника: ");
        int age = scanner.nextInt();
        System.out.print("Введите зарплату сотрудника: ");
        double salary = scanner.nextDouble();
        System.out.print("Введите ID отдела для сотрудника: ");
        long departmentId = scanner.nextLong();

        Department department = departmentService.getDepartment(departmentId);
        if (department != null) {
            Employee employee = new Employee(fullName, age, salary);
            employee.setDepartment(department);
            employeeService.addEmployee(employee);
            System.out.println("Сотрудник добавлен.");
        } else {
            System.out.println("Отдел не найден.");
        }
    }

    private void deleteDepartment() {
        System.out.print("Введите ID отдела для удаления: ");
        long id = scanner.nextLong();
        scanner.nextLine(); // consume newline
        departmentService.deleteDepartment(id);
        System.out.println("Отдел удален.");
    }

    private void deleteEmployee() {
        System.out.print("Введите ID сотрудника для удаления: ");
        long id = scanner.nextLong();
        scanner.nextLine(); // consume newline
        employeeService.deleteEmployee(id);
        System.out.println("Сотрудник удален.");
    }

    private void editDepartment() {
        System.out.print("Введите ID отдела для редактирования: ");
        long id = scanner.nextLong();
        scanner.nextLine(); // consume newline
        System.out.print("Введите новое название отдела: ");
        String newName = scanner.nextLine();
        Department newDepartment = new Department(newName);
        departmentService.updateDepartment(id, newDepartment);
        System.out.println("Отдел обновлен.");
    }

    private void editEmployee() {
        System.out.print("Введите ID сотрудника для редактирования: ");
        long id = scanner.nextLong();
        scanner.nextLine(); // consume newline
        System.out.print("Введите новое ФИО сотрудника: ");
        String fullName = scanner.nextLine();
        System.out.print("Введите новый возраст сотрудника: ");
        int age = scanner.nextInt();
        System.out.print("Введите новую зарплату сотрудника: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        Employee newEmployee = new Employee(fullName, age, salary);
        employeeService.updateEmployee(id, newEmployee);
        System.out.println("Сотрудник обновлен.");
    }

    private void showEmployeesInDepartment() {
        System.out.print("Введите ID отдела: ");
        long departmentId = scanner.nextLong();
        scanner.nextLine(); // consume newline
        Department department = departmentService.getDepartment(departmentId);
        if (department != null) {
            List<Employee> employees = department.getEmployees();
            if (employees.isEmpty()) {
                System.out.println("В этом отделе нет сотрудников.");
            } else {
                employees.forEach(e -> System.out.printf("ID: %d, ФИО: %s, Возраст: %d, Зарплата: %.2f\n",
                        e.getId(), e.getFullName(), e.getAge(), e.getSalary()));
            }
        } else {
            System.out.println("Отдел не найден.");
        }
    }

    private void showAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        if (departments.isEmpty()) {
            System.out.println("Отделы не найдены.");
        } else {
            departments.forEach(d -> System.out.printf("ID: %d, Название: %s, Количество сотрудников: %d\n",
                    d.getId(), d.getName(), d.getEmployeeCount()));
        }
    }

    private void showTotalSalaryInDepartment() {
        System.out.print("Введите ID отдела: ");
        long departmentId = scanner.nextLong();
        scanner.nextLine(); // consume newline
        Department department = departmentService.getDepartment(departmentId);
        if (department != null) {
            double totalSalary = department.getTotalSalary();
            System.out.printf("Общая сумма зарплат в отделе '%s': %.2f\n", department.getName(), totalSalary);
        } else {
            System.out.println("Отдел не найден.");
        }
    }
}
