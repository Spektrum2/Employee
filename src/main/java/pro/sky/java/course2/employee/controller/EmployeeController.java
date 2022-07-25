package pro.sky.java.course2.employee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.employee.model.Employee;
import pro.sky.java.course2.employee.service.EmployeeService;
import pro.sky.java.course2.employee.exceptions.EmployeeAlreadyAddedException;
import pro.sky.java.course2.employee.exceptions.EmployeeNotFoundException;
import pro.sky.java.course2.employee.exceptions.EmployeeStorageIsFullException;

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
        return employeeService.addEmployee(name, surname);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam("firstName") String name,
                                   @RequestParam("lastName") String surname) {
        return employeeService.removeEmployee(name, surname);

    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam("firstName") String name,
                                 @RequestParam("lastName") String surname) {
        return employeeService.findEmployee(name, surname);
    }
}
