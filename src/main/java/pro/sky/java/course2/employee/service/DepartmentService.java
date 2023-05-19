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

    /**
     * Метод возвращает всех сотрудников с разделением по отделам.
     *
     * @return Возвращает список всех сотрудников с разделением по отделам
     */
    public Map<Integer, List<Employee>> printAllDepartmentEmployee() {
        return employeeService.printEmployee().values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    /**
     * Метод возвращает всех сотрудников по отделу
     *
     * @param department Отдел
     * @return Возвращает всех сотрудников по отделу
     */
    public List<Employee> printDepartmentEmployee(Integer department) {
        return employeeService.printEmployee().values().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    /**
     * Метод возвращает сотрудника с максимальной зарплатой на основе номера отдела
     *
     * @param department Отдел
     * @return Возвращает сотрудника с максимальной зарплатой
     */
    public Employee getMinSalaryEmployee(Integer department) {
        return employeeService.printEmployee().values().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(e -> (int) e.getSalary()))
                .orElseThrow(DepartmentNotFoundException::new);
    }

    /**
     * Метод возвращает сотрудника с минимальной зарплатой на основе номера отдела
     *
     * @param department Отдел
     * @return Возвращает сотрудника с минимальной зарплатой
     */
    public Employee getMaxSalaryEmployee(Integer department) {
        return employeeService.printEmployee().values().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(e -> (int) e.getSalary()))
                .orElseThrow(DepartmentNotFoundException::new);
    }
}
