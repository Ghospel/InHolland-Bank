package io.swagger.api;

import io.swagger.model.Customer;

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
public class CustomerApiControllerIntegrationTest {

    @Autowired
    private CustomerApi api;

    @Test
    public void customerPostTest() throws Exception {
        Customer customer = new Customer();
        ResponseEntity<Customer> responseEntity = api.customerPost(customer);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void customerGetTest() throws Exception {
        ResponseEntity<List<Customer>> responseEntity = api.customerGet();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void customerIdGetTest() throws Exception {
        Integer id = 5;
        ResponseEntity<Customer> responseEntity = api.customerIdGet(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void customerIdDeleteTest() throws Exception {
        Integer id = 1;
        ResponseEntity<Void> responseEntity = api.customerIdDelete(id);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
}
