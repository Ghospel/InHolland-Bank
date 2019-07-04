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

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeApiControllerIntegrationTest {

    @Autowired
    private EmployeeApi api;

    @Test
    public void employeePostTest() throws Exception {
        Employee employee = new Employee();
        ResponseEntity<Employee> responseEntity = api.employeePost(employee);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void employeeGetTest() throws Exception {
        ResponseEntity<List<Employee>> responseEntity = api.employeeGet();
        assertThat(responseEntity.getStatusCode(), anyOf(is(HttpStatus.OK), is(HttpStatus.NO_CONTENT)));
    }

    @Test
    public void employeeIdGetTest() throws Exception {
        Integer id = 3;
        ResponseEntity<Employee> responseEntity = api.employeeIdGet(id);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void employeeIdDeleteTest() throws Exception {
        Integer id = 3;
        ResponseEntity<Void> responseEntity = api.employeeIdDelete(id);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

}
