package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.model.Customer;
import io.swagger.model.InlineResponse200;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
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
        return new ResponseEntity<List<Account>>(service.listAllAccounts(), HttpStatus.OK);
    }

    public ResponseEntity<InlineResponse200> accountsIbanBalanceGet(@ApiParam(value = "",required=true) @PathVariable("iban") String iban) {
        String accept = request.getHeader("Accept");
        InlineResponse200 res = new InlineResponse200();
        res.setBalance(service.findAccountById(iban).getBalance());
        return new ResponseEntity<InlineResponse200>(res, HttpStatus.OK);
    }

    public ResponseEntity<Void> accountsIbanDelete(@ApiParam(value = "",required=true) @PathVariable("iban") String iban) {
        String accept = request.getHeader("Accept");
        service.deleteAccountById(iban);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Account> accountsIbanGet(@ApiParam(value = "",required=true) @PathVariable("iban") String iban) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Account>(service.findAccountById(iban), HttpStatus.OK);
    }

    public ResponseEntity<Void> accountsIbanMinimumbalancePut(@ApiParam(value = "",required=true) @PathVariable("iban") String iban,@ApiParam(value = "",required=true) @PathVariable("minimumbalance") Float minimumbalance) {
        String accept = request.getHeader("Accept");
        service.findAccountById(iban).setMinimalBalance(minimumbalance);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> accountsPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Account body) {
        String accept = request.getHeader("Accept");
        service.saveAccount(body);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
