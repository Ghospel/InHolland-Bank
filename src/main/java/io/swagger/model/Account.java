package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;
import java.util.Objects;

/**
 * Account
 */
@Entity
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-27T18:08:08.076Z[GMT]")
public class Account   {

  @Id
  private String IBAN = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("customer")
  private Long customer = null;

  @JsonProperty("balance")
  private Float balance = null;

  @JsonProperty("minimalBalance")
  private Float minimalBalance = 0f;

  @JsonProperty("daylimit")
  private Float daylimit = 10000f;

  public Account IBAN(String IBAN) {
    this.IBAN = IBAN;
    return this;
  }

  /**
   * Get IBAN
   * @return IBAN
  **/
  @ApiModelProperty(example = "NL01INHO0000000002", value = "")

  public String getIBAN() {
    return IBAN;
  }

  public void setIBAN(String IBAN) {
    this.IBAN = IBAN;
  }

  public Account type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Account customer(Long customer) {
    this.customer = customer;
    return this;
  }

  /**
   * Get customer
   * @return customer
  **/
  @ApiModelProperty(value = "")

  @Valid
  public long getCustomer() {
    return customer;
  }

  public void setCustomer(Long customer) {
    this.customer = customer;
  }

  public Account balance(Float balance) {
    this.balance = balance;
    return this;
  }

  /**
   * Get balance
   * @return balance
  **/
  @ApiModelProperty(example = "352.42", value = "")

  public Float getBalance() {
    return balance;
  }

  public void setBalance(Float balance) {
    this.balance = balance;
  }

  public Account minimalBalance(Float minimalBalance) {
    this.minimalBalance = minimalBalance;
    return this;
  }

  /**
   * Get minimalBalance
   * @return minimalBalance
  **/
  @ApiModelProperty(example = "-2000", value = "")

  public Float getMinimalBalance() {
    return minimalBalance;
  }

  public void setMinimalBalance(Float minimalBalance) {
    this.minimalBalance = minimalBalance;
  }

  public Account daylimit(Float daylimit) {
    this.daylimit = daylimit;
    return this;
  }

  /**
   * Get daylimit
   * @return daylimit
  **/
  @ApiModelProperty(example = "10000", value = "")

  public Float getDaylimit() {
    return daylimit;
  }

  public void setDaylimit(Float daylimit) {
    this.daylimit = daylimit;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(this.IBAN, account.IBAN) &&
        Objects.equals(this.type, account.type) &&
        Objects.equals(this.customer, account.customer) &&
        Objects.equals(this.balance, account.balance) &&
        Objects.equals(this.minimalBalance, account.minimalBalance) &&
        Objects.equals(this.daylimit, account.daylimit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(IBAN, type, customer, balance, minimalBalance, daylimit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    
    sb.append("    IBAN: ").append(toIndentedString(IBAN)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    customer: ").append(toIndentedString(customer)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    minimalBalance: ").append(toIndentedString(minimalBalance)).append("\n");
    sb.append("    daylimit: ").append(toIndentedString(daylimit)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
