package com.prograd.EmployeeManagement.controllers;

import com.prograd.EmployeeManagement.models.Employee;
import com.prograd.EmployeeManagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/employee")
public class EmployeeUserController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("{id}")
    @PreAuthorize("authentication.principal.id == #id")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id")int id)
    {
        Employee am = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(am, HttpStatus.OK);
    }
}
