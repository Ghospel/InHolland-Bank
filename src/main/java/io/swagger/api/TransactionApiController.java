package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.threeten.bp.OffsetDateTime;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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

        Account from = service.findAccountById(transaction.getFromIBAN());
        Account to = service.findAccountById(transaction.getRecipientIBAN());

        //set the datetime of the transaction
        transaction.setDate(OffsetDateTime.now());

        if(transaction.getAmount() < 0 || transaction.getAmount() > 2000){ //TODO: this is the transaction limit..
            return new ResponseEntity<Transaction>(HttpStatus.BAD_REQUEST);
        }

        if(from.getBalance() - transaction.getAmount() < from.getMinimalBalance()){
            return new ResponseEntity<Transaction>(HttpStatus.BAD_REQUEST);
        }

        if(transaction.getType() == Transaction.TypeEnum.WITHDRAWAL){
            if(transaction.getRecipientIBAN() != null || transaction.getFromIBAN() == null){
                return new ResponseEntity<Transaction>(HttpStatus.BAD_REQUEST);
            }
            if(from != null){
                from.setBalance(from.getBalance() - transaction.getAmount());
                service.saveTransaction(transaction);
                service.saveAccount(from);
                return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
            }
        }

        if(transaction.getType() == Transaction.TypeEnum.DEPOSIT && transaction.getFromIBAN() != null){
            if(transaction.getFromIBAN() != null){
                return new ResponseEntity<Transaction>(HttpStatus.BAD_REQUEST);
            }
            if(to != null){
                to.setBalance(to.getBalance() - transaction.getAmount());
                service.saveTransaction(transaction);
                service.saveAccount(to);
                return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
            }
        }

        //if the transaction is to or from savings, ensure that it is on the accounts of the same customer
        if(transaction.getType() == Transaction.TypeEnum.TOSAVINGS || transaction.getType() == Transaction.TypeEnum.FROMSAVINGS){
            if(to.getCustomer() == from.getCustomer()){
                from.setBalance(from.getBalance() - transaction.getAmount());
                to.setBalance(to.getBalance() + transaction.getAmount());
                service.saveTransaction(transaction);
                service.saveAccount(from);
                service.saveAccount(to);
                return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
            }
        }

        //ensure that both accounts exists when depositing transferring money
        if(transaction.getType() == Transaction.TypeEnum.TRANSFER) {
            if(to != null && from != null) {
                from.setBalance(from.getBalance() - transaction.getAmount());
                to.setBalance(to.getBalance() + transaction.getAmount());
                service.saveTransaction(transaction);
                service.saveAccount(from);
                service.saveAccount(to);
                service.saveTransaction(transaction);
                return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
            }
        }



        return new ResponseEntity<Transaction>(HttpStatus.BAD_REQUEST);
    }

}
