package io.swagger.api;

import io.swagger.model.Transaction;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionApiControllerIntegrationTest {

    @Autowired
    private TransactionApi api;

    @Test
    public void transactionGetTest() throws Exception {
        ResponseEntity<List<Transaction>> responseEntity = api.transactionGet();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void transactionIdGetTest() throws Exception {
        Integer id = 56;
        ResponseEntity<Transaction> responseEntity = api.transactionIdGet(id);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void transactionPostTest() throws Exception {
        Transaction transaction = new Transaction();
        ResponseEntity<Transaction> responseEntity = api.transactionPost(transaction);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
