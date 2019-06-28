package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.Customer;
import io.swagger.model.Employee;
import io.swagger.model.Transaction;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.CustomerRepository;
import io.swagger.repository.EmployeeRepository;
import io.swagger.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;
    private TransactionRepository transactionRepository;

    public BankService(AccountRepository accountRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, TransactionRepository transactionRepository){
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.transactionRepository = transactionRepository;
    }
    // ACCOUNTS
    public List<Account> listAllAccounts(){
        return(List<Account>) accountRepository.findAll();
    }

    public Account findAccountById(String IBAN){
        return accountRepository.findOne(IBAN);
    }

    public void saveAccount(Account account){
        accountRepository.save(account);
    }

    public void setMinimumBalance(Account account, float minBalance){
        account.setMinimalBalance(minBalance);
    }

    public void deleteAccountById(String IBAN){
        accountRepository.delete(findAccountById(IBAN));
    }

    //CUSTOMERS
    public List<Customer> listAllCustomers(){
        return(List<Customer>) customerRepository.findAll();
    }

    public Customer findCustomerById(long id){
        return customerRepository.findOne(id);
    }

    public void saveCustomer(Customer customer){
        customerRepository.save(customer);
    }

    public void deleteCustomerById(long id){
        customerRepository.delete(findCustomerById(id));
    }

    //EMPLOYEES
    public List<Employee> listAllEmployees(){
        return(List<Employee>) employeeRepository.findAll();
    }

    public Employee findEmployeeById(long id){
        return employeeRepository.findOne(id);
    }

    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public void deleteEmployeeById(long id){
        employeeRepository.delete(findEmployeeById(id));
    }

    //TRRANSACTIONS
    public List<Transaction> listAllTransactions(){
        return(List<Transaction>) transactionRepository.findAll();
    }

    public Transaction findTransactionById(long id){
        return transactionRepository.findOne(id);
    }

    public void saveTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }

    public void deleteTransactionById(long id){
        transactionRepository.delete(findTransactionById(id));
    }


}
