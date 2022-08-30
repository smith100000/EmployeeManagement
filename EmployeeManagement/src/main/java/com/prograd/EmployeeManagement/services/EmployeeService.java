package com.prograd.EmployeeManagement.services;

import com.prograd.EmployeeManagement.models.Employee;

import java.util.List;

public interface EmployeeService {
    Boolean saveEmployee(Employee employee);
    List<Employee> getAllEmployee();
    Employee getEmployeeById(int id);
    Employee updateEmployee(Employee employee,int id);
    void deleteEmployee(int id);
}
