package pro.sky.java.course2.employee.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pro.sky.java.course2.employee.exceptions.DepartmentNotFoundException;
import pro.sky.java.course2.employee.model.Employee;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static pro.sky.java.course2.employee.constants.EmployeeServiceTestConstants.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    private final Map<String, Employee> employees1 = new HashMap<>();
    private final Map<String, Employee> employees2 = new HashMap<>();

    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentService out;

    @BeforeEach
    public void setUp() {
        Employee employee1 = new Employee("Алексей", "Дементьев", 4, 30_000);
        Employee employee2 = new Employee("Александр", "Левин", 4, 20_000);
        Employee employee3 = new Employee("Майя", "Михайлова", 5, 70_000);

        employees1.put(employee1.getFullName(), employee1);
        employees1.put(employee2.getFullName(), employee2);
        employees1.put(employee3.getFullName(), employee3);

        Employee employee4 = new Employee("Алексей", "Дементьев", 4, 30_000);
        Employee employee5 = new Employee("Александр", "Левин", 4, 20_000);
        Employee employee6 = new Employee("Майя", "Михайлова", 5, 70_000);

        employees2.put(employee4.getFullName(), employee4);
        employees2.put(employee5.getFullName(), employee5);
        employees2.put(employee6.getFullName(), employee6);
    }

    @Test
    public void shouldCallDepartmentServiceWhenPrintAllDepartmentEmployee() {
        when(employeeService.printEmployee()).thenReturn(employees1);

        Map<Integer, List<Employee>> expected = out.printAllDepartmentEmployee();

        Map<Integer, List<Employee>> actual = employees2.values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        assertEquals(expected, actual);
    }

    @Test
    public void shouldCallDepartmentServiceWhenPrintDepartmentEmployee() {
        when(employeeService.printEmployee()).thenReturn(employees1);

        List<Employee> expected = out.printDepartmentEmployee(DEPARTMENT_SERVICE);

        List<Employee> actual = employees2.values().stream()
                .filter(e -> e.getDepartment() == DEPARTMENT_SERVICE).toList();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldCallDepartmentServiceWhenGetMinSalaryEmployee() {
        when(employeeService.printEmployee()).thenReturn(employees1);

        Employee expected = out.getMinSalaryEmployee(DEPARTMENT_SERVICE);

        Employee actual = employees2.values().stream()
                .filter(e -> e.getDepartment() == DEPARTMENT_SERVICE)
                .min(Comparator.comparingInt(e -> (int) e.getSalary()))
                .orElseThrow(DepartmentNotFoundException::new);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldCallDepartmentServiceWhenGetMaxSalaryEmployee() {
        when(employeeService.printEmployee()).thenReturn(employees1);

        Employee expected = out.getMaxSalaryEmployee(DEPARTMENT_SERVICE);

        Employee actual = employees2.values().stream()
                .filter(e -> e.getDepartment() == DEPARTMENT_SERVICE)
                .max(Comparator.comparingInt(e -> (int) e.getSalary()))
                .orElseThrow(DepartmentNotFoundException::new);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptions() {
        assertThrows(DepartmentNotFoundException.class, () -> out.getMinSalaryEmployee(DEPARTMENT_THROW));
        assertThrows(DepartmentNotFoundException.class, () -> out.getMaxSalaryEmployee(DEPARTMENT_THROW));
    }

}