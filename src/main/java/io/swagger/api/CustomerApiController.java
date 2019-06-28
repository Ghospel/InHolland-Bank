package io.swagger.api;

import io.swagger.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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
        String accept = request.getHeader("Accept");
        return new ResponseEntity<List<Customer>>(service.listAllCustomers(), HttpStatus.OK);
    }

    public ResponseEntity<Void> customerIdDelete(@ApiParam(value = "",required=true) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        service.deleteCustomerById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Customer> customerIdGet(@ApiParam(value = "",required=true) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Customer>(service.findCustomerById(id), HttpStatus.OK);
    }

    public ResponseEntity<Customer> customerPost(@RequestBody @Validated Customer customer) {
        String accept = request.getHeader("Accept");
        service.saveCustomer(customer);
        return new ResponseEntity<Customer>(HttpStatus.CREATED);
    }

}
