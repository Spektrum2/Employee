package pro.sky.java.course2.employee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Отдел не найден")
public class DepartmentNotFoundException extends RuntimeException{
}
