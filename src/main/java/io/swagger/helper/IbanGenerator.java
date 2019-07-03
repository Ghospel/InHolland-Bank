package io.swagger.helper;

import io.swagger.model.Account;
import io.swagger.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IbanGenerator {

    public static String generateIban(BankService service){
        List<Account> accounts = service.listAllAccounts();
        Long accountNumber = Long.parseLong(accounts.get(accounts.size()-1).getIBAN().substring(9));
        String incremented = String.format("%0" + 9 + "d", (accountNumber) + 1);
        return "NL01INHO0" + incremented;
    }
}
