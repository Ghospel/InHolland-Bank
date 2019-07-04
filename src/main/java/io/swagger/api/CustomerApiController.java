package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Customer;
import io.swagger.service.BankService;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-27T18:08:08.076Z[GMT]")
@Controller
public class CustomerApiController implements CustomerApi {

    private static final Logger log = LoggerFactory.getLogger(CustomerApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private BankService service;

    @org.springframework.beans.factory.annotation.Autowired
    public CustomerApiController(BankService service, ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.service = service;
    }

    public ResponseEntity<List<Customer>> customerGet() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<GrantedAuthority> ls;
        //Collection<GrantedAuthority> auths = (Collection<GrantedAuthority>) SecurityContextHolder.getPrincipal getAuthentication().getAuthorities();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            ls = (Collection<GrantedAuthority>) authentication.getAuthorities();
            //authentication.getPrincipal().
        }
        String accept = request.getHeader("Accept");
        List<Customer> customers = service.listAllCustomers();
        return customers == null || customers.isEmpty() ? new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT) : new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    public ResponseEntity<Void> customerIdDelete(@ApiParam(value = "",required=true) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        Customer customer = service.findCustomerById(id);
        service.deleteCustomerById(id);
        return  customer == null ? new ResponseEntity<Void>(HttpStatus.NOT_FOUND) : new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Customer> customerIdGet(@ApiParam(value = "",required=true) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        Customer customer = service.findCustomerById(id);
        return customer == null ? new ResponseEntity<Customer>(HttpStatus.NOT_FOUND) : new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    public ResponseEntity<Customer> customerPost(@RequestBody @Validated Customer customer) {
        String accept = request.getHeader("Accept");
        service.saveCustomer(customer);
        return new ResponseEntity<Customer>(HttpStatus.CREATED);
    }

}
