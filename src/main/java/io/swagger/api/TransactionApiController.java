package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.model.Transaction;
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
public class TransactionApiController implements TransactionApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private BankService service;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionApiController(BankService service, ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.service = service;
    }

    public ResponseEntity<List<Transaction>> transactionGet() {
        String accept = request.getHeader("Accept");
        List<Transaction> transactions = service.listAllTransactions();

        return transactions == null || transactions.isEmpty() ? new ResponseEntity<List<Transaction>>(HttpStatus.NO_CONTENT) : new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
    }

    public ResponseEntity<Transaction> transactionIdGet(@ApiParam(value = "",required=true) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        Transaction transaction = service.findTransactionById(id);
        return transaction == null ? new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND) : new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
    }

    public ResponseEntity<Transaction> transactionPost(@RequestBody Transaction transaction) {
        String accept = request.getHeader("Accept");

        //if the transaction is to or from savings, ensure that it is on the accounts of the same customer
        if(transaction.getType() == Transaction.TypeEnum.TOSAVINGS || transaction.getType() == Transaction.TypeEnum.FROMSAVINGS){
            if(service.findAccountById(transaction.getRecipientIBAN()).getCustomer() != service.findAccountById(transaction.getFromIBAN()).getCustomer()){
                service.saveTransaction(transaction);
                return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
            }
        }
        return new ResponseEntity<Transaction>(HttpStatus.METHOD_NOT_ALLOWED);
    }

}
