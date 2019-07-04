package io.swagger;

import io.swagger.model.*;
import io.swagger.service.BankService;
import io.swagger.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "io.swagger", "io.swagger.api" , "io.swagger.configuration"})
public class Swagger2SpringBoot implements CommandLineRunner {

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, BankService service) throws Exception {
        Employee henk = new Employee("employee", "henk", "employee", Arrays.asList(new Role("EMPLOYEE")));
        Customer piet = new Customer("user", "piet", "user", Arrays.asList(new Role("USER")));
        service.saveCustomer(piet);
        service.saveEmployee(henk);

        //create the banks own account
        Customer bankCustomer = new Customer();
        bankCustomer.setName("Inholland Bank Account");
        service.saveCustomer(bankCustomer);
        Account bankAccount = new Account();
        bankAccount.setCustomer(bankCustomer.getId());
        bankAccount.setIBAN("NL01INHO0000000001");
        bankAccount.setBalance(2000f);
        bankAccount.setMinimalBalance(0f);
        bankAccount.setDaylimit(10000f);
        bankAccount.setType("Checking");
        bankAccount.setBalance(500f);
        service.saveAccount(bankAccount);
        builder.userDetailsService(s -> new CustomUserDetails(service.findUserByName(s)) {
        });

    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}
