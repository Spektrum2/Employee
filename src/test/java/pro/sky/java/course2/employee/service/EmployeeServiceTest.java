package pro.sky.java.course2.employee.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.employee.exceptions.*;
import pro.sky.java.course2.employee.model.Employee;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.java.course2.employee.constants.EmployeeServiceTestConstants.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    private final Employee employee = new Employee(NAME, SURNAME, DEPARTMENT, SALARY);

    @Mock
    private ValidatorService validatorService;
    @InjectMocks
    private EmployeeService out;


    @BeforeEach
    public void setUp() {
        Employee employee1 = new Employee("София", "Нефедова", 2, 50_000);

        when(validatorService.validatorEmployee(any(), any(), any(), any())).thenReturn(employee1);

        out.addEmployee("София", "Нефедова", 2, 50_000);
    }

    @Test
    public void shouldCallEmployeeServiceWhenPrintEmployee() {
        Map<String, Employee> expected = out.printEmployee();

        Map<String, Employee> actual = new HashMap<>();

        actual.put(employee.getFullName(), employee);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldCallEmployeeServiceWhenAddEmployee() {
        Employee expected = new Employee("Алексей", "Дементьев", 3, 30_000);

       when(validatorService.validatorEmployee(any(), any(), any(), any())).thenReturn(expected);

        Employee actual = out.addEmployee("Алексей", "Дементьев", 3, 30_000);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldCallEmployeeServiceWhenRemoveEmployee() {
        Employee result = out.removeEmployee(NAME, SURNAME);

        assertEquals(employee, result);
    }

    @Test
    public void shouldCallEmployeeServiceWhenFindEmployee() {
        Employee result = out.findEmployee(NAME, SURNAME);

        assertEquals(employee, result);
    }

    @Test
    public void shouldThrowExceptions() {
        assertThrows(DepartmentMoreLessException.class, () -> out.addEmployee(NAME, SURNAME, DEPARTMENT_THROW, SALARY));
        assertThrows(DepartmentMoreLessException.class, () -> out.addEmployee(NAME, SURNAME, DEPARTMENT_THROW2, SALARY));
        assertThrows(EmployeeAlreadyAddedException.class, () -> out.addEmployee(NAME, SURNAME, DEPARTMENT, SALARY));
        assertThrows(EmployeeNotFoundException.class, () -> out.removeEmployee(NAME_THROW, SURNAME_THROW));
        assertThrows(EmployeeNotFoundException.class, () -> out.findEmployee(NAME_THROW, SURNAME_THROW));
    }

    @Test
    public void shouldThrowEmployeeStorageIsFullException() {
        Employee employee1 = new Employee("Марк", "Захаров", 1, 15_000);
        Employee employee2 = new Employee("Алексей", "Дементьев", 3, 30_000);
        Employee employee3 = new Employee("Злата", "Мещерякова", 5, 80_000);
        Employee employee4 = new Employee("Александр", "Левин", 4, 20_000);
        Employee employee5 = new Employee("Майя", "Михайлова", 5, 70_000);
        Employee employee6 = new Employee("Дмитрий", "Федоров", 4, 60_000);
        Employee employee7 = new Employee("Георгий", "Гаврилов", 3, 40_000);
        Employee employee8 = new Employee("Алексей", "Герасимов", 2, 35_000);
        Employee employee9 = new Employee("Мадина", "Яковлева", 1, 90_000);
        Employee employee10 = new Employee(NAME2, SURNAME2, 1, 90_000);

        when(validatorService.validatorEmployee(any(), any(), any(), any())).thenReturn(employee1);

        out.addEmployee("Марк", "Захаров", 1, 15_000);

        when(validatorService.validatorEmployee(any(), any(), any(), any())).thenReturn(employee2);

        out.addEmployee("Алексей", "Дементьев", 3, 30_000);

        when(validatorService.validatorEmployee(any(), any(), any(), any())).thenReturn(employee3);

        out.addEmployee("Злата", "Мещерякова", 5, 80_000);

        when(validatorService.validatorEmployee(any(), any(), any(), any())).thenReturn(employee4);

        out.addEmployee("Александр", "Левин", 4, 20_000);

        when(validatorService.validatorEmployee(any(), any(), any(), any())).thenReturn(employee5);

        out.addEmployee("Майя", "Михайлова", 5, 70_000);

        when(validatorService.validatorEmployee(any(), any(), any(), any())).thenReturn(employee6);

        out.addEmployee("Дмитрий", "Федоров", 4, 60_000);

        when(validatorService.validatorEmployee(any(), any(), any(), any())).thenReturn(employee7);

        out.addEmployee("Георгий", "Гаврилов", 3, 40_000);

        when(validatorService.validatorEmployee(any(), any(), any(), any())).thenReturn(employee8);

        out.addEmployee("Алексей", "Герасимов", 2, 35_000);

        when(validatorService.validatorEmployee(any(), any(), any(), any())).thenReturn(employee9);

        out.addEmployee("Мадина", "Яковлева", 1, 90_000);

        when(validatorService.validatorEmployee(any(), any(), any(), any())).thenReturn(employee10);

        assertThrows(EmployeeStorageIsFullException.class, () -> out.addEmployee(NAME2, SURNAME2, DEPARTMENT, SALARY));
    }
}
