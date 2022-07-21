package pro.sky.java.course2.employee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.employee.domain.Employee;
import pro.sky.java.course2.employee.service.EmployeeService;
import pro.sky.java.course2.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.course2.exceptions.EmployeeNotFoundException;
import pro.sky.java.course2.exceptions.EmployeeStorageIsFullException;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> printEmployee() {
        return employeeService.printEmployee();
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam("firstName") String name,
                                @RequestParam("lastName") String surname) {
        Employee employee = new Employee(
                name,
                surname
        );
        if (employeeService.printListSize() >= 10) {
            throw new EmployeeStorageIsFullException();
        } else if (employeeService.findEmployee(employee) != null) {
            throw new EmployeeAlreadyAddedException();
        } else {
            employeeService.addEmployee(employee);
            return employee;
        }
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam("firstName") String name,
                                   @RequestParam("lastName") String surname) {
        Employee employee = new Employee(
                name,
                surname
        );
        if (employeeService.removeEmployee(employee)) {
            return employee;
        } else {
            throw new EmployeeNotFoundException();
        }

    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam("firstName") String name,
                                 @RequestParam("lastName") String surname) {
        Employee employee = new Employee(
                name,
                surname
        );
        if (employeeService.findEmployee(employee) == null) {
            throw new EmployeeNotFoundException();
        } else {
            return employeeService.findEmployee(employee);
        }
    }
}
