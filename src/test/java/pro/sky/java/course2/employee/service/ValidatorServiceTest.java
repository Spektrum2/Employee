package pro.sky.java.course2.employee.service;

import org.junit.jupiter.api.Test;
import pro.sky.java.course2.employee.exceptions.EmployeeInvalidNameException;
import pro.sky.java.course2.employee.exceptions.EmployeeInvalidSurnameException;
import pro.sky.java.course2.employee.model.Employee;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static pro.sky.java.course2.employee.constants.EmployeeServiceTestConstants.*;

public class ValidatorServiceTest {

    private final ValidatorService out = new ValidatorService();

    @Test
    public void shouldCallValidatorServiceWhenValidatorEmployee() {
        Employee actual = out.validatorEmployee(NAME, SURNAME, DEPARTMENT, SALARY);

        Employee expected = new Employee(NAME, SURNAME, DEPARTMENT, SALARY);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldThrowExceptions() {
        assertThatExceptionOfType(EmployeeInvalidNameException.class)
                .isThrownBy(() -> out.validatorEmployee(NAME_THROW_VALIDATOR, SURNAME, DEPARTMENT, SALARY));
        assertThatExceptionOfType(EmployeeInvalidSurnameException.class)
                .isThrownBy(() -> out.validatorEmployee(NAME, SURNAME_THROW_VALIDATOR, DEPARTMENT, SALARY));
    }
}
