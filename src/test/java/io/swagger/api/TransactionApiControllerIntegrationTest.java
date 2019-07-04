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

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionApiControllerIntegrationTest {

    @Autowired
    private TransactionApi api;

    @Test
    public void transactionPostTest() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setFromIBAN("NL01INHO0000000001");
        transaction.setAmount(0f);
        transaction.setRecipientIBAN("NL01INHO0000000001");
        transaction.setDescription("Unit test");
        transaction.setPerformerID(5l);
        transaction.setType(Transaction.TypeEnum.TRANSFER);
        ResponseEntity<Transaction> responseEntity = api.transactionPost(transaction);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void transactionGetTest() throws Exception {
        ResponseEntity<List<Transaction>> responseEntity = api.transactionGet();
        assertThat(responseEntity.getStatusCode(), anyOf(is(HttpStatus.NO_CONTENT), is(HttpStatus.OK)));
    }

    @Test
    public void transactionIdGetTest() throws Exception {
        Integer id = 1;
        ResponseEntity<Transaction> responseEntity = api.transactionIdGet(id);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
}
