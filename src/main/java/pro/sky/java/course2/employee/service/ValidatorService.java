package pro.sky.java.course2.employee.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.employee.exceptions.EmployeeInvalidNameException;
import pro.sky.java.course2.employee.exceptions.EmployeeInvalidSurnameException;
import pro.sky.java.course2.employee.model.Employee;

@Service
public class ValidatorService {
    public Employee validatorEmployee(String name, String surname, Integer dept, Integer pay) {
        if (!StringUtils.isAlpha(name)) {
            throw new EmployeeInvalidNameException();
        } else if (!StringUtils.isAlpha(surname)) {
            throw new EmployeeInvalidSurnameException();
        }
        return new Employee(StringUtils.capitalize(name.toLowerCase()), StringUtils.capitalize(surname.toLowerCase()), dept, pay);
    }
}
