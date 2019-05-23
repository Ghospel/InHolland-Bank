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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerApiControllerIntegrationTest {

    @Autowired
    private CustomerApi api;

    @Test
    public void customerGetTest() throws Exception {
        ResponseEntity<Customer> responseEntity = api.customerGet();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void customerIdDeleteTest() throws Exception {
        Integer id = 56;
        ResponseEntity<Void> responseEntity = api.customerIdDelete(id);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void customerIdGetTest() throws Exception {
        Integer id = 56;
        ResponseEntity<Customer> responseEntity = api.customerIdGet(id);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void customerPostTest() throws Exception {
        ResponseEntity<Customer> responseEntity = api.customerPost();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
