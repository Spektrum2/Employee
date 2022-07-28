package pro.sky.java.course2.employee.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.employee.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.course2.employee.exceptions.EmployeeNotFoundException;
import pro.sky.java.course2.employee.exceptions.EmployeeStorageIsFullException;
import pro.sky.java.course2.employee.model.Employee;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {
    private static final int LIMIT = 10;
    Map<String, Employee> employees = new HashMap<>();

    public Map<String, Employee> printEmployee() {
        return employees;
    }

    public Employee addEmployee(String name, String surname) {

        Employee employee = new Employee(name, surname);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.put(employee.getFullName(), employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    public Employee removeEmployee(String name, String surname) {
        Employee employee = new Employee(name, surname);
        if (employees.containsKey(employee.getFullName())) {
            employees.remove(employee.getFullName());
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    public Employee findEmployee(String name, String surname) {
        Employee employee = new Employee(name, surname);
        if (employees.containsKey(employee.getFullName())) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }
}
