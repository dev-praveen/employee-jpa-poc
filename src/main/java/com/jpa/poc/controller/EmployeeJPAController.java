package com.jpa.poc.controller;

import com.jpa.poc.models.Employee;
import com.jpa.poc.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EmployeeJPAController {

    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/employee")
    public ResponseEntity<Employee> getEmployee(@RequestParam("id") Integer empId){

        final Optional<Employee> employeeOptional = repository.findById(empId.longValue());
        employeeOptional.orElseThrow(() -> new IllegalArgumentException("Employee doesn't exist with the id "+empId));

        return new ResponseEntity<>(employeeOptional.get(), HttpStatus.OK);
    }

    @PostMapping("/create/employee")
    public ResponseEntity<Void> addEmployee(@RequestBody Employee employee){
        repository.save(employee);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){

        List<Employee> employeeList = new ArrayList<>();
        final Iterable<Employee> employees = repository.findAll();
        employees.forEach(employee -> employeeList.add(employee));

        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @PostMapping("/create/employeedept")
    public ResponseEntity<Void> addEmployeeWithDepartment(@RequestBody Employee employee){
        repository.save(employee);
        return ResponseEntity.noContent().build();
    }
}
