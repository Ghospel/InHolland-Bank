package io.swagger.api;

import io.swagger.model.Employee;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeApiControllerIntegrationTest {

    @Autowired
    private EmployeeApi api;

    @Test
    public void employeeGetTest() throws Exception {
        ResponseEntity<List<Employee>> responseEntity = api.employeeGet();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void employeeIdDeleteTest() throws Exception {
        Integer id = 56;
        ResponseEntity<Void> responseEntity = api.employeeIdDelete(id);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void employeeIdGetTest() throws Exception {
        Integer id = 56;
        ResponseEntity<Employee> responseEntity = api.employeeIdGet(id);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void employeePostTest() throws Exception {
        Employee employee = new Employee();
        ResponseEntity<Employee> responseEntity = api.employeePost(employee);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

}
