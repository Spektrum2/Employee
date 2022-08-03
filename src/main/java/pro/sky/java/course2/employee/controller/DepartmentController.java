package pro.sky.java.course2.employee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.employee.exceptions.DepartmentNotFoundException;
import pro.sky.java.course2.employee.model.Employee;
import pro.sky.java.course2.employee.service.DepartmentService;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("all")
    public Map<Integer, String> printDepartmentEmployee(@RequestParam(name = "departmentId", required = false) Integer department) {
        if (department == null) {
            return departmentService.printAllDepartmentEmployee();
        }
        return departmentService.printDepartmentEmployee(department);
    }

    @GetMapping("/min-salary")
    public Optional<Employee> getMinSalaryEmployee(@RequestParam("departmentId") Integer department) {
        return Optional.ofNullable(departmentService.getMinSalaryEmployee(department)
                .orElseThrow(DepartmentNotFoundException::new));
    }

    @GetMapping("/max-salary")
    public Optional<Employee> getMaxSalaryEmployee(@RequestParam("departmentId") Integer department) {
        return Optional.ofNullable(departmentService.getMaxSalaryEmployee(department)
                .orElseThrow(DepartmentNotFoundException::new));
    }
}
