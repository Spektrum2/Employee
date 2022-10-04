package pro.sky.java.course2.employee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Неправильно указан номер отдела. Укажите номер отдела от 1 до 5!")
public class DepartmentMoreLessException extends RuntimeException {
}
