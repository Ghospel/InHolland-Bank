package io.swagger;

import io.swagger.model.Role;
import io.swagger.model.User;
import io.swagger.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Arrays;

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
        service.saveUser(new User("user", "user", Arrays.asList(new Role("USER"), new Role("ACTUATOR")), true));

        builder.userDetailsService(s -> new CustomUserDetails(service.findUserByName(s)));

    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}
