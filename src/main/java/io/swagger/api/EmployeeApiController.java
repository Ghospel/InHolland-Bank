package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Employee;
import io.swagger.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-27T18:08:08.076Z[GMT]")
@Controller
public class EmployeeApiController implements EmployeeApi {

    private static final Logger log = LoggerFactory.getLogger(EmployeeApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private BankService service;

    @org.springframework.beans.factory.annotation.Autowired
    public EmployeeApiController(BankService service, ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.service = service;
    }

    public ResponseEntity<List<Employee>> employeeGet() {
        String accept = request.getHeader("Accept");
        List<Employee> employees = service.listAllEmployees();
        return employees == null || employees.isEmpty() ? new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT) : new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    public ResponseEntity<Void> employeeIdDelete(@ApiParam(value = "",required=true) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        Employee employee = service.findEmployeeById(id);
        if(employee != null){
            service.deleteEmployeeById(id);
        }
        return employee == null ? new ResponseEntity<Void>(HttpStatus.NOT_FOUND) : new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Employee> employeeIdGet(@ApiParam(value = "",required=true) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        Employee employee = service.findEmployeeById(id);
        return employee == null ? new ResponseEntity<Employee>(HttpStatus.NOT_FOUND) : new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    public ResponseEntity<Employee> employeePost(@RequestBody Employee employee) {
        String accept = request.getHeader("Accept");
        service.saveEmployee(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }

}
