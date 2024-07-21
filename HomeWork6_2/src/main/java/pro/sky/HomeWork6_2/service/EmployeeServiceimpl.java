package pro.sky.HomeWork6_2.service;

import jakarta.annotation.PostConstruct;
import pro.sky.HomeWork6_2.exeption.EmployeeAlreadyAddedException;
import pro.sky.HomeWork6_2.exeption.EmployeeNotFoundExeption;
import pro.sky.HomeWork6_2.exeption.EmployeeStorageIsFullException;
import pro.sky.HomeWork6_2.model.Employee;


import java.util.*;

public class EmployeeServiceimpl implements EmployeeService {
    private final int maxEmployee = 15;
    private final Map<String, Employee> employees = new HashMap<>();
    @PostConstruct
    private void init(){
        addEmployee("Ivan","Ivanov",1,10_000);
        addEmployee("Dmitriy","Petrov",2,20_000);
        addEmployee("Petr","Sidorov",3,30_000);
        addEmployee("Sergey","Loginov",4,10_000);
        addEmployee("Andrey","Ivanov",1,20_200);
        addEmployee("Ivan","Hicolaev",1,15_000);
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int department, double salary) {
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
