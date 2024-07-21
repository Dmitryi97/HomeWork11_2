package pro.sky.HomeWork6_2.service;

import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.HomeWork6_2.exeption.EmployeeAlreadyAddedException;
import pro.sky.HomeWork6_2.exeption.EmployeeNotFoundExeption;
import pro.sky.HomeWork6_2.exeption.EmployeeStorageIsFullException;
import pro.sky.HomeWork6_2.model.Employee;


import java.util.*;
@Service
public class EmployeeServiceimpl implements EmployeeService {
    private final int maxEmployee = 15;
    private final Map<String, Employee> employees = new HashMap<>();
    private final  ValidationServise validationServise;

    public EmployeeServiceimpl(ValidationServise validationServise) {
        this.validationServise = validationServise;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int department, double salary) {
        firstName = validationServise.validateCheckName(firstName);
        lastName = validationServise.validateCheckName(lastName);
        String key = buildKey(firstName, lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() >= maxEmployee) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName, department, salary);
        employees.put(key, employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        String key = buildKey(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundExeption();
        }
        return employees.remove(key);
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String key = buildKey(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundExeption();
        }
        return employees.get(key);
    }

    private String buildKey(String firstName, String lastName) {
        return firstName + lastName;
    }

    @Override
    public List<Employee> findAll() {
        return List.copyOf(employees.values());
    }
}
