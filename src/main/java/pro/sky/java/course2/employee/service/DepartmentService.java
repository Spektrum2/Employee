package pro.sky.java.course2.employee.service;

import org.springframework.stereotype.Service;

import pro.sky.java.course2.employee.exceptions.DepartmentNotFoundException;
import pro.sky.java.course2.employee.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Map<Integer, List<Employee>> printAllDepartmentEmployee() {
        return employeeService.printEmployee().values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public List<Employee> printDepartmentEmployee(Integer department) {
        return employeeService.printEmployee().values().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Employee getMinSalaryEmployee(Integer department) {
        return employeeService.printEmployee().values().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(e -> (int) e.getSalary()))
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public Employee getMaxSalaryEmployee(Integer department) {
        return employeeService.printEmployee().values().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(e -> (int) e.getSalary()))
                .orElseThrow(DepartmentNotFoundException::new);
    }
}
