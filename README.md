# 2.3. Введение в Maven и Spring Boot

## Какие задачи нужно выполнить:

1. Перенести из курсовой работу с массивом сотрудников в сервис на базе веб-приложения на Spring. 
2. Заменить сообщения об ошибках выбросом исключений.  
3. Заменить массивы в коде на листы.

### Шаг 1

Создать Spring Boot проект.

### Шаг 2

Подключить модуль Spring Web.

### Шаг 3

Перенести из курсовой класс Employee, оставив в нем только поля firstName и lastName, конструктор, геттеры и методы hashCode, equals, toString.

### Шаг 4

Создать сервис EmployeeService, который хранит внутри поле с массивом сотрудников.

### Шаг 5

Реализовать в сервисе три метода, которые принимают в качестве параметров firstName и lastName:

1. Добавить сотрудника.
2. Удалить сотрудника.
3. Найти сотрудника.

### Шаг 6

Написать собственное непроверяемое исключение EmployeeNotFoundException, которое выбрасывается, если сотрудник не найден. 

### Шаг 7

Написать собственное непроверяемое исключение EmployeeStorageIsFullException, которое выбрасывается, если массив сотрудников переполнен.

### Шаг 8

Написать собственное непроверяемое исключение EmployeeAlreadyAddedException, которое выбрасывается, когда нового сотрудника пытаются добавить в массив, а в массиве уже есть такой сотрудник.  **

### Шаг 9

Добавить в методы из шага 5 новые исключения.

1. В метод с добавлением сотрудника нужно добавить выброс исключения из шага 7 в ситуации, когда массив переполнен.
2. В метод с добавлением сотрудника нужно добавить выброс исключения из шага 8 в ситуации, когда добавляемый сотрудник уже имеется в массиве.
3. В метод с удалением сотрудника нужно добавить выброс исключения из шага 6 в ситуации, когда удаляемый сотрудник не найден.
4. В метод с поиском сотрудника нужно добавить выброс исключения из шага 6 в ситуации, когда сотрудник не найден.

### Шаг 10

Реализовать EmployeeController, который имеет поле EmployeeService. 

Объявить конструктор с этим параметром, чтобы заинджектить EmployeeService в EmployeeController.

### Шаг 11

Объявить в контроллере 3 метода:

1. По адресу /employee/add?firstName=Ivan&lastName=Ivanov должен вернуться объект Employee в формате JSON, т. е. { "firstName": "Ivan", "lastName": "Ivanov" }, или исключение ArrayIsFull в случае переполнения массива или EmployeeAlreadyAdded в случае, когда сотрудник уже существует.
2. По адресу /employee/remove?firstName=Ivan&lastName=Ivanov должен вернуться объект Employee в формате JSON, т. е. { "firstName": "Ivan", "lastName": "Ivanov" }, или исключение со статусом EmployeeNotFound, если сотрудник отсутствует.
3. По адресу /employee/find?firstName=Ivan&lastName=Ivanov должен вернуться объект Employee в формате JSON, т. е. { "firstName": "Ivan", "lastName": "Ivanov" }, или исключение со статусом EmployeeNotFound, если такой сотрудник отсутствует.

### **Шаг 12**

Заменить массивы на листы. В методах, которые принимают массивы, также провести изменения. 

### Шаг 13

Написать метод, который выводит в браузер список всех сотрудников в формате JSON (необходимо вернуть объект списка). 

### Подсказки

1. Сервис должен быть помечен аннотацией @Service.
2. Контроллер должен быть помечен аннотацией @RestController.
3. Возврат статуса осуществляется с помощью аннотации @ResponseStatus.
4. Никакой логики в контроллере быть не должно. Всю работу с массивом выполняет сервис, а контроллер только формирует сообщение с результатом работы сервиса.
5. В случае отсутствия одного из параметров Spring должен самостоятельно выводить страницу с ошибкой (аннотация @RequestParam).
6. Чтобы не вводить часть с /employee в каждый @GetMapping, следует использовать аннотацию @RequestMapping на уровне контроллера.
7. Spring сам конвертирует ваш объект Employee в JSON. Достаточно просто вернуть его через return в методе контроллера.

# 2.7. **Коллекции: многообразие реализаций**

Ваше задание на сегодня: 

1. Перенести проект с EmployeeBook на Map в качестве хранилища сотрудников.
2. Продумать контракт для ключей, чтобы сотрудник с одним ФИО существовал только в одном экземпляре, корректно добавлялся и удалялся.
3. Избавиться от циклов в методах по поиску сотрудника, заменив на методы Map.
4. Переработать все методы по работе с хранилищем на работу с методами Map.
- **Критерии оценки**
    - Все сотрудники существуют только в одном экземпляре
    - Поиск сотрудников осуществляется с помощью метода
    - Все методы по работе с хранилищем заменены на мапы
    - Любого сотрудника можно удалить
    - Можно добавить нового сотрудника
    - Проект перенесен на map

# 2.8. **Stream API и Optional**

Шаг 1. Добавить в поле Employee поля «Зарплата» и «Отдел» из курсовой работы.

Шаг 2. В текущий проект на Spring перенести методы работы с отделами из курсовой работы. 

Шаг 3. Заменить реализацию через циклы на Stream API. Написать новый контроллер и сервис, которые будут:

1. Возвращать сотрудника с максимальной зарплатой на основе номера отдела, который приходит в запрос из браузера.
    
    ```java
    /departments/max-salary?departmentId=5
    ```
    
2.  Возвращать сотрудника с минимальной зарплатой на основе номера отдела.
    
    ```java
     /departments/min-salary?departmentId=5 
    ```
    
3. Возвращать всех сотрудников по отделу. 
    
    ```java
    /departments/all?departmentId=5
    ```
    
4. Возвращать всех сотрудников с разделением по отделам.
    
    ```java
     /departments/all
    ```
# 2.10 Библиотеки

**Шаг 1**

Подключите к вашему проекту из прошлых домашних заданий (книге сотрудников) библиотеку **commons-lang3** (с помощью Maven). 

**Шаг 2**

Реализуйте проверку всех входящих от пользователя текстовых данных (имена сотрудников и фамилии) с помощью класса **StringUtils**.

Если проверка не пройдена, выбросить исключение, возвращающее статус 400 Bad Request.

**Шаг 3**

Если данные прошли проверку, убедиться, что имя и фамилия будут записаны в сотрудника с большой буквы.
