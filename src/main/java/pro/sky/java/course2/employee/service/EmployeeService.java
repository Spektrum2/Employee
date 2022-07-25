package pro.sky.java.course2.employee.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.employee.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.course2.employee.exceptions.EmployeeNotFoundException;
import pro.sky.java.course2.employee.exceptions.EmployeeStorageIsFullException;
import pro.sky.java.course2.employee.model.Employee;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private static final int LIMIT = 10;
    List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Марк", "Захаров"),
            new Employee("София", "Нефедова"),
            new Employee("Алексей", "Дементьев"),
            new Employee("Александр", "Левин"),
            new Employee("Майя", "Михайлова"),
            new Employee("Майя", "Попова"),
            new Employee("Злата", "Мещерякова"),
            new Employee("Дмитрий", "Федоров"),
            new Employee("Георгий", "Гаврилов"),
            new Employee("Мадина", "Яковлева")
    ));

    public List<Employee> printEmployee() {
        return employees;
    }

    public Integer printListSize() {
        return employees.size();
    }

    public Employee addEmployee(String name, String surname) {

        Employee employee = new Employee(name, surname);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    public Employee removeEmployee(String name, String surname) {
        Employee employee = new Employee(name, surname);
        if (employees.contains(employee)) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    public Employee findEmployee(String name, String surname) {
        Employee employee = new Employee(name, surname);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }
}
