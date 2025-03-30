package org.gaurav.simpleapi.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import org.gaurav.simpleapi.model.StatusType;
import org.gaurav.simpleapi.validation.ActiveEnumValidation;

import java.util.Date;
import java.util.Objects;

@SequenceGenerator(
        name = "sequenceId_generator",
        sequenceName = "transaction_seq", allocationSize = 1)
@Entity
public final class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceId_generator")
    private int TransactionId;


    private Date transactionTime;
    private Integer customerId;
    private Integer quantity;
    private String productCode;

    @Max(value = 5000, message = "Total cost of transaction must not exceed 5000")
    private Integer cost;

    private String location;

    @Transient
    @ActiveEnumValidation(message = "Product must be active")
    private StatusType productStatus;

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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setProductStatus(StatusType productStatus) {
        this.productStatus = productStatus;
    }
}
