package io.swagger.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.util.Optional;

@Configuration
@EnableWebSecurity
@Order(2)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
    private ApiKeyRepository apiKeyRepository;
    public ApiSecurityConfig(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Value("test")
    private String principalRequestValue;

    @Value("X-AUTHTOKEN")
    private String headerName;
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        ApiKeyAuthFilter filter = new ApiKeyAuthFilter(headerName);
        filter.setAuthenticationManager(authentication -> {
            String principal = (String) authentication.getPrincipal();
            Optional<ApiKey> key = Optional.ofNullable(apiKeyRepository.findOne(principal));

            System.out.println(principal);

            if(principal.equals(principalRequestValue)){

                authentication.setAuthenticated(true);
            }

            /*if (!key.isPresent()) {
                throw new BadCredentialsException("API Key was not found on the system");
            }
            authentication.setAuthenticated(true);*/

            return authentication;


        });

        httpSecurity
                .antMatcher("/**")
                .csrf().disable()    // disable X-site request forgery
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // If Stateless every request needs authentication
                .and()
                .addFilter(filter).authorizeRequests() // authorize all requests that has a correct header value
                .anyRequest().authenticated(); // all requests are authenticated
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("{noop}password") // {noop} means password is being sent without encoding
                .roles("USER") // user has role USER
                .and()
                .withUser("admin")
                .password("{noop}password")
                .roles("ADMIN"); // admin has role ADMIN
    }
}
