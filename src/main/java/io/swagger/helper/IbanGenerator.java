package io.swagger.helper;

import io.swagger.model.Account;
import io.swagger.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IbanGenerator {

    @Autowired
    private BankService bankService;

    public String generateIban(){
        List<Account> accounts = bankService.listAllAccounts();
        Long accountNumber = Long.parseLong(accounts.get(accounts.size()-1).getIBAN().substring(9));
        return "NL01INHO0" + ++accountNumber;
    }
}
