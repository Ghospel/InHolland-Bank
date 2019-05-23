/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Employee;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-05-23T17:08:49.313Z[GMT]")
@Api(value = "employee", description = "the employee API")
public interface EmployeeApi {

    @ApiOperation(value = "Get all the employees", nickname = "employeeGet", notes = "", response = Employee.class, authorizations = {
        @Authorization(value = "password", scopes = {
            @AuthorizationScope(scope = "", description = "")            })    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Employee.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 498, message = "Token expired"),
        @ApiResponse(code = 499, message = "Token required"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/employee",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Employee> employeeGet();


    @ApiOperation(value = "Delete the employee", nickname = "employeeIdDelete", notes = "", authorizations = {
        @Authorization(value = "password", scopes = {
            @AuthorizationScope(scope = "", description = "")            })    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 498, message = "Token expired"),
        @ApiResponse(code = 499, message = "Token required"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/employee/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> employeeIdDelete(@ApiParam(value = "",required=true) @PathVariable("id") Integer id);


    @ApiOperation(value = "Get the employee", nickname = "employeeIdGet", notes = "", response = Employee.class, authorizations = {
        @Authorization(value = "password", scopes = {
            @AuthorizationScope(scope = "", description = "")            })    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Employee.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 498, message = "Token expired"),
        @ApiResponse(code = 499, message = "Token required"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/employee/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Employee> employeeIdGet(@ApiParam(value = "",required=true) @PathVariable("id") Integer id);


    @ApiOperation(value = "Create a new employee", nickname = "employeePost", notes = "", response = Employee.class, authorizations = {
        @Authorization(value = "password", scopes = {
            @AuthorizationScope(scope = "", description = "")            })    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Employee.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 498, message = "Token expired"),
        @ApiResponse(code = 499, message = "Token required"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/employee",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Employee> employeePost();

}
