package pro.sky.java.course2.employee.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.employee.domain.Employee;
import pro.sky.java.course2.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.course2.exceptions.EmployeeNotFoundException;
import pro.sky.java.course2.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
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

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }


    public boolean removeEmployee(Employee employee) {
        return employees.remove(employee);
    }


    public Employee findEmployee(Employee employee) {

        for (Employee worker : employees) {
            if (worker.equals(employee)) {
                return worker;
            }
        }
        return null;
    }
}
