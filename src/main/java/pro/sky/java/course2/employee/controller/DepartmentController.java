package pro.sky.java.course2.employee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.employee.model.Employee;
import pro.sky.java.course2.employee.service.DepartmentService;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/all")
    public Map<Integer, List<Employee>> printAllDepartmentEmployee() {
        return departmentService.printAllDepartmentEmployee();
    }

    @GetMapping(value = "/all", params = "departmentId")
    public List<Employee> printDepartmentEmployee(@RequestParam(name = "departmentId", required = false) Integer department) {
        return departmentService.printDepartmentEmployee(department);
    }

    @GetMapping("/min-salary")
    public Employee getMinSalaryEmployee(@RequestParam("departmentId") Integer department) {
        return departmentService.getMinSalaryEmployee(department);

    }

    @GetMapping("/max-salary")
    public Employee getMaxSalaryEmployee(@RequestParam("departmentId") Integer department) {
        return departmentService.getMaxSalaryEmployee(department);

    }
}
