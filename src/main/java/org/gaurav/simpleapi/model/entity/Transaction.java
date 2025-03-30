package org.gaurav.simpleapi.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;
import org.gaurav.simpleapi.model.StatusType;
import org.gaurav.simpleapi.validation.ActiveEnumValidation;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@SequenceGenerator(
        name = "sequenceId_generator",
        sequenceName = "transaction_seq", allocationSize = 1)
@Entity
@Getter
@Setter
public final class Transaction  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceId_generator")
    private int TransactionId;


    private Date transactionTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
    private Customer customer;
    private Integer quantity;
    private String productCode;

    @Max(value = 5000, message = "Total cost of transaction must not exceed 5000")
    private Integer cost;

    @Transient
    @ActiveEnumValidation(message = "Product must be active")
    private StatusType productStatus;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(int transactionId) {
        TransactionId = transactionId;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public StatusType getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(StatusType productStatus) {
        this.productStatus = productStatus;
    }
}
