package pro.sky.java.course2.employee.service;

import org.springframework.stereotype.Service;

import pro.sky.java.course2.employee.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> printDepartmentEmployee(Integer department) {
        return employeeService.employees.values().stream()
                .filter(e -> e.getDepartment() == department).collect(Collectors.toList());
    }

    public Optional<Employee> getMinSalaryEmployee(Integer department) {
        return employeeService.employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(e -> (int) e.getSalary()));
    }

    public Optional<Employee> getMaxSalaryEmployee(Integer department) {
        return employeeService.employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(e -> (int) e.getSalary()));
    }

    public Map<Integer, String> printAllDepartmentEmployee() {
        return employeeService.employees.values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.mapping(Employee::getFullName,
                                Collectors.joining(", ", "{", "}"))));
    }
}
