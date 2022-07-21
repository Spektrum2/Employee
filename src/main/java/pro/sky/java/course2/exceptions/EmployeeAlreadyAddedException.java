package pro.sky.java.course2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Сотрудник уже имеется в массиве")
public class EmployeeAlreadyAddedException extends RuntimeException {
}
