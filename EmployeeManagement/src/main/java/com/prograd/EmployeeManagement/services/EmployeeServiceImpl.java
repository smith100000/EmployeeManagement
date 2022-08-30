package com.prograd.EmployeeManagement.services;
import com.prograd.EmployeeManagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.prograd.EmployeeManagement.models.Employee;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }
    @Override
    public Boolean saveEmployee(Employee employee) {

        List<Employee>emp=employeeRepository.findAll();
        boolean isFound=false;
        for (Employee e:emp)
        {
            if (e.getEmail().equals(employee.getEmail())){
                isFound=true;
                break;
            }
        }

        if (isFound)
        {
            return false;
        }
        else {
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            employeeRepository.save(employee);
            return true;
        }
    }

    @Override
    public List<Employee> getAllEmployee() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElseThrow();

    }

    @Override
    public Employee updateEmployee(Employee employee, int id) {
        Employee existingEmployee=employeeRepository.findById(id).orElseThrow();
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setBranch(employee.getBranch());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setDob(employee.getDob());
        existingEmployee.setAge(employee.getAge());
        existingEmployee.setGender(employee.getGender());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        existingEmployee.setPassword(passwordEncoder.encode(employee.getPassword()));

        employeeRepository.save(existingEmployee);

        return existingEmployee;

    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.findById(id);
        employeeRepository.deleteById(id);
    }
}
