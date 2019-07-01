package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.model.Customer;
import io.swagger.model.InlineResponse200;

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
public class AccountsApiControllerIntegrationTest {

    @Autowired
    private AccountsApi api;

    @Test
    public void accountsGetTest() throws Exception {
        ResponseEntity<List<Account>> responseEntity = api.accountsGet();
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void accountsIbanBalanceGetTest() throws Exception {
        String iban = "iban_example";
        ResponseEntity<InlineResponse200> responseEntity = api.accountsIbanBalanceGet(iban);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void accountsIbanDeleteTest() throws Exception {
        String iban = "iban_example";
        ResponseEntity<Void> responseEntity = api.accountsIbanDelete(iban);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void accountsIbanGetTest() throws Exception {
        String iban = "iban_example";
        ResponseEntity<Account> responseEntity = api.accountsIbanGet(iban);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void accountsIbanMinimumbalancePutTest() throws Exception {
        String iban = "iban_example";
        Float minimumbalance = 3.4F;
        ResponseEntity<Void> responseEntity = api.accountsIbanMinimumbalancePut(iban, minimumbalance);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void accountsPostTest() throws Exception {
        Account body = new Account();
        body.setIBAN("asdfasdfasdf");
        ResponseEntity<Void> responseEntity = api.accountsPost(body);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

}
