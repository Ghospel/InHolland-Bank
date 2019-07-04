package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.model.InlineResponse200;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountsApiControllerIntegrationTest {

    @Autowired
    private AccountsApi api;

    @Test
    public void accountsGetTest() throws Exception {
        ResponseEntity<List<Account>> responseEntity = api.accountsGet();
        assertThat(responseEntity.getStatusCode(), anyOf(is(HttpStatus.NO_CONTENT), is(HttpStatus.OK)));
    }

    @Test
    public void accountsIbanBalanceGetTest() throws Exception {
        String iban = "NL01INHO0000000001";
        ResponseEntity<InlineResponse200> responseEntity = api.accountsIbanBalanceGet(iban);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void accountsIbanDeleteTest() throws Exception {
        String iban = "NL01INHO0000000001";
        ResponseEntity<Void> responseEntity = api.accountsIbanDelete(iban);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void accountsIbanGetTest() throws Exception {
        String iban = "NL01INHO0000000001";
        ResponseEntity<Account> responseEntity = api.accountsIbanGet(iban);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void accountsIbanMinimumbalancePutTest() throws Exception {
        String iban = "NL01INHO0000000001";
        Float minimumbalance = 500F;
        ResponseEntity<Void> responseEntity = api.accountsIbanMinimumbalancePut(iban, minimumbalance);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void accountsPostTest() throws Exception {
        Account body = new Account();
        body.setCustomer(5l);
        ResponseEntity<Void> responseEntity = api.accountsPost(body);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

}
