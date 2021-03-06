/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.Account;
import io.swagger.model.Customer;
import io.swagger.model.InlineResponse200;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-27T18:08:08.076Z[GMT]")
@Api(value = "accounts", description = "the accounts API")
public interface AccountsApi {

    @ApiOperation(value = "Get all the accounts", nickname = "accountsGet", notes = "", response = Account.class, authorizations = {
        @Authorization(value = "password", scopes = {
            @AuthorizationScope(scope = "", description = "")            })    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Account.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 498, message = "Token expired"),
        @ApiResponse(code = 499, message = "Token required"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/api/accounts",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Account>> accountsGet();


    @ApiOperation(value = "Get all the accounts for one customer", nickname = "accountsGetForCustomer", notes = "", response = Account.class, authorizations = {
            @Authorization(value = "password", scopes = {
                    @AuthorizationScope(scope = "", description = "")            })    }, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Account.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 498, message = "Token expired"),
            @ApiResponse(code = 499, message = "Token required"),
            @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/api/accounts/{customer}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Account>> accountsGetForCustomer(@PathVariable("customer") Long customer);

    @ApiOperation(value = "Get balance with the provided IBAN", nickname = "accountsIbanBalanceGet", notes = "", response = InlineResponse200.class, authorizations = {
        @Authorization(value = "password", scopes = {
            @AuthorizationScope(scope = "", description = "")            })    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = InlineResponse200.class),
        @ApiResponse(code = 400, message = "invalid IBAN"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 498, message = "Token expired"),
        @ApiResponse(code = 499, message = "Token required"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/api/accounts/{iban}/balance",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<InlineResponse200> accountsIbanBalanceGet(@ApiParam(value = "",required=true) @PathVariable("iban") String iban);


    @ApiOperation(value = "Delete the account with the provided IBAN", nickname = "accountsIbanDelete", notes = "", authorizations = {
        @Authorization(value = "password", scopes = {
            @AuthorizationScope(scope = "", description = "")            })    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "invalid ID"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 498, message = "Token expired"),
        @ApiResponse(code = 499, message = "Token required"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/api/accounts/{iban}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> accountsIbanDelete(@ApiParam(value = "",required=true) @PathVariable("iban") String iban);


    @ApiOperation(value = "Get the account with the provided IBAN", nickname = "accountsIbanGet", notes = "", response = Account.class, authorizations = {
        @Authorization(value = "password", scopes = {
            @AuthorizationScope(scope = "", description = "")            })    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Account.class),
        @ApiResponse(code = 400, message = "invalid ID"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 498, message = "Token expired"),
        @ApiResponse(code = 499, message = "Token required"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/api/accounts/{iban}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Account> accountsIbanGet(@ApiParam(value = "",required=true) @PathVariable("iban") String iban);


    @ApiOperation(value = "Set the minimum balance with the provided IBAN", nickname = "accountsIbanMinimumbalancePut", notes = "", authorizations = {
        @Authorization(value = "password", scopes = {
            @AuthorizationScope(scope = "", description = "")            })    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "invalid IBAN"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 498, message = "Token expired"),
        @ApiResponse(code = 499, message = "Token required"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/api/accounts/{iban}/{minimumbalance}",
        method = RequestMethod.PUT)
    ResponseEntity<Void> accountsIbanMinimumbalancePut(@ApiParam(value = "",required=true) @PathVariable("iban") String iban,@ApiParam(value = "",required=true) @PathVariable("minimumbalance") Float minimumbalance);


    @ApiOperation(value = "Create an account", nickname = "accountsPost", notes = "", authorizations = {
        @Authorization(value = "password", scopes = {
            @AuthorizationScope(scope = "", description = "")            })    }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 498, message = "Token expired"),
        @ApiResponse(code = 499, message = "Token required"),
        @ApiResponse(code = 500, message = "Internal server error") })
    @RequestMapping(value = "/api/accounts",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> accountsPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Account body);

}
