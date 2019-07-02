package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.helper.IbanGenerator;
import io.swagger.model.Account;
import io.swagger.model.InlineResponse200;
import io.swagger.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-27T18:08:08.076Z[GMT]")
@Controller
public class AccountsApiController implements AccountsApi {

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private BankService service;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountsApiController(BankService service, ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.service = service;
    }

    public ResponseEntity<List<Account>> accountsGet() {
        String accept = request.getHeader("Accept");
        List<Account> accounts = service.listAllAccounts();
        return accounts == null || accounts.isEmpty() ? new ResponseEntity<List<Account>>(HttpStatus.NO_CONTENT) : new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
    }

    public ResponseEntity<InlineResponse200> accountsIbanBalanceGet(@ApiParam(value = "",required=true) @PathVariable("iban") String iban) {
        String accept = request.getHeader("Accept");
        InlineResponse200 res = new InlineResponse200();
        Account acc = service.findAccountById(iban);
        if(acc == null){
            return new ResponseEntity<InlineResponse200>(HttpStatus.NOT_FOUND);
        } else if (acc.getBalance() == null){
            return new ResponseEntity<InlineResponse200>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        res.setBalance(acc.getBalance());
        return new ResponseEntity<InlineResponse200>(res, HttpStatus.OK);
    }

    public ResponseEntity<Void> accountsIbanDelete(@ApiParam(value = "",required=true) @PathVariable("iban") String iban) {
        String accept = request.getHeader("Accept");
        Account account = service.findAccountById(iban);
        service.deleteAccountById(iban);
        return account == null ? new ResponseEntity<Void>(HttpStatus.NOT_FOUND) : new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Account> accountsIbanGet(@ApiParam(value = "",required=true) @PathVariable("iban") String iban) {
        String accept = request.getHeader("Accept");
        Account acc = service.findAccountById(iban);
        return acc == null ? new ResponseEntity<Account>(HttpStatus.NOT_FOUND) : new ResponseEntity<Account>(service.findAccountById(iban), HttpStatus.OK);
    }

    public ResponseEntity<Void> accountsIbanMinimumbalancePut(@ApiParam(value = "",required=true) @PathVariable("iban") String iban,@ApiParam(value = "",required=true) @PathVariable("minimumbalance") Float minimumbalance) {
        String accept = request.getHeader("Accept");
        Account acc = service.findAccountById(iban);
        if(acc == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        service.findAccountById(iban).setMinimalBalance(minimumbalance);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> accountsPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Account body) {
        String accept = request.getHeader("Accept");
        IbanGenerator generator = new IbanGenerator();
        body.setIBAN(generator.generateIban());
        service.saveAccount(body);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

}
