package com.prograd.EmployeeManagement.controllers;

import com.prograd.EmployeeManagement.models.Assets;
import com.prograd.EmployeeManagement.models.Employee;
import com.prograd.EmployeeManagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }
    @PostMapping
    public ResponseEntity<String> saveEmployee(@RequestBody @Valid Employee employee){

        if(employeeService.saveEmployee(employee))
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        else
            return new ResponseEntity<>("USER ALREADY EXISTS",HttpStatus.BAD_REQUEST);
    }
    @GetMapping
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id")int id)
    {
        try {
            Employee am = employeeService.getEmployeeById(id);
            return new ResponseEntity<>(am, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id")int id,@RequestBody Employee employee)
    {
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id), HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id")int id)
    {
        try{
            employeeService.deleteEmployee(id);
            return new ResponseEntity<String>(" data deleted successfully",HttpStatus.OK);

        }
        catch (EmptyResultDataAccessException e){
            return new ResponseEntity<String>("Employee data Not Found",HttpStatus.NOT_FOUND);
        }
    }
}