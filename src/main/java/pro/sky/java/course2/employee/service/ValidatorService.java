package pro.sky.java.course2.employee.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.employee.exceptions.EmployeeInvalidNameException;
import pro.sky.java.course2.employee.exceptions.EmployeeInvalidSurnameException;
import pro.sky.java.course2.employee.model.Employee;

@Service
public class ValidatorService {
    /**
     * Метод для проверки входящих от пользователя текстовых данных (проверяет, содержит ли заданная строка только символы Юникода). Переводит в верхний регистор первые буквы фамилии и имени
     *
     * @param name Имя сотрудника
     * @param surname Фамилия сотрудника
     * @param dept Отдел сотрудника
     * @param pay Зарплата сотрудника
     * @return Возвращает сотрудника
     */
    public Employee validatorEmployee(String name, String surname, Integer dept, Integer pay) {
        if (!StringUtils.isAlpha(name)) {
            throw new EmployeeInvalidNameException();
        } else if (!StringUtils.isAlpha(surname)) {
            throw new EmployeeInvalidSurnameException();
        }
        return new Employee(StringUtils.capitalize(name.toLowerCase()), StringUtils.capitalize(surname.toLowerCase()), dept, pay);
    }
}
