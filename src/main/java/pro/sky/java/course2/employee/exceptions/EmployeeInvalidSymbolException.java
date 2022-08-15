package pro.sky.java.course2.employee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Введен неверный символ")
public class EmployeeInvalidSymbolException extends RuntimeException{
}
