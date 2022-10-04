package pro.sky.java.course2.employee.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.employee.exceptions.*;
import pro.sky.java.course2.employee.model.Employee;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {
    private final ValidatorService validatorService;

    public EmployeeService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    private static final int LIMIT = 10;
    private final Map<String, Employee> employees = new HashMap<>();

    public Map<String, Employee> printEmployee() {
        return new HashMap<>(employees);
    }

    public Employee addEmployee(String name, String surname, Integer dept, Integer pay) {
        Employee employee = validatorService.validatorEmployee(name, surname, dept, pay);
        String key = getKey(employee.getFirstName(), employee.getLastName());
        if (dept < 0 || dept > 5) {
            throw new DepartmentMoreLessException();
        }
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT) {
            employees.put(key, employee);
            return employee;
        }

        throw new EmployeeStorageIsFullException();
    }

    public Employee removeEmployee(String name, String surname) {
        String key = getKey(name, surname);
        if (employees.containsKey(key)) {
            return employees.remove(key);
        }
        throw new EmployeeNotFoundException();
    }

    public Employee findEmployee(String name, String surname) {
        String key = getKey(name, surname);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(key);
    }

    private static String getKey(String name, String surname) {
        return name + " " + surname;
    }
}
