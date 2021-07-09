package com.jpa.poc.controller;

import com.jpa.poc.models.Department;
import com.jpa.poc.models.Employee;
import com.jpa.poc.repo.EmployeeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class EmployeeJPAControllerTest {

    @InjectMocks
    private EmployeeJPAController controller;

    @Mock
    private EmployeeRepository repository;

    @Test
    public void getEmployee() {

        Department department = new Department(1070, "car-shed");
        Employee employee1 = new Employee(110L, "praveen", "rtpp", department);

        when(repository.findById(any())).thenReturn(Optional.of(employee1));
        final ResponseEntity<Employee> employee = controller.getEmployee(110);
        Assert.assertEquals(employee.getBody().getAddress(), employee1.getAddress());

    }

    @Test(expected = IllegalArgumentException.class)
    public void getEmployee_exception_test() {

        Department department = new Department(1060, "pharma");
        Employee employee1 = new Employee(110L, "praveen", "rtpp", department);
        when(repository.findById(111L)).thenReturn(Optional.of(employee1));
        final ResponseEntity<Employee> employee = controller.getEmployee(110);
    }

    @Test
    public void addEmployee() {

        Department department = new Department(1040, "non-veg");
        Employee employee = new Employee(110L, "praveen", "rtpp", department);
        when(repository.save(any(Employee.class))).thenReturn(employee);
        final ResponseEntity<Void> responseEntity = controller.addEmployee(employee);

        Assert.assertEquals(responseEntity.getStatusCodeValue(), 204);

    }

    @Test
    public void getAllEmployees() {

        Department department1 = new Department(1000, "store");
        Department department2 = new Department(1020, "vegetables");
        Department department3 = new Department(1030, "groceery");

        Employee employee1 = new Employee(110L, "praveen", "rtpp", department1);
        Employee employee2 = new Employee(120L, "kiran", "pdtr", department2);
        Employee employee3 = new Employee(130L, "laddu", "chmkr", department3);

        final List<Employee> employees = Arrays.asList(employee1, employee2, employee3);
        when(repository.findAll()).thenReturn(employees);

        final ResponseEntity<List<Employee>> allEmployees = controller.getAllEmployees();
        final List<Employee> body = allEmployees.getBody();

        Assert.assertEquals(body.size(), 3);
        Assert.assertEquals(body.get(0).getName(), "praveen");

    }
}