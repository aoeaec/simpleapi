package org.gaurav.simpleapi.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import org.gaurav.simpleapi.model.StatusType;
import org.gaurav.simpleapi.validation.ActiveEnumValidation;

import java.time.LocalDate;
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

    public Date getTransactionTime() {
        return transactionTime;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    private final Date transactionTime;
    private final Integer customerId;
    private final Integer quantity;
    private final String productCode;

    @Transient
    @Max(value = 300, message = "Cost cannot be more than 300")
    private Integer cost;

    @Transient
   @ActiveEnumValidation(message = "Only Active enum allowed")
    private StatusType statusType;

    public Transaction(Date transactionTime,
                       Integer customerId,
                       Integer quantity,
                       String productCode) {
        this.transactionTime = transactionTime;
        this.customerId = customerId;
        this.quantity = quantity;
        this.productCode = productCode;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Transaction) obj;
        return Objects.equals(this.transactionTime, that.transactionTime) &&
                Objects.equals(this.customerId, that.customerId) &&
                Objects.equals(this.quantity, that.quantity) &&
                Objects.equals(this.productCode, that.productCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionTime, customerId, quantity, productCode);
    }

    @Override
    public String toString() {
        return "Transaction[" +
                "transactionTime=" + transactionTime + ", " +
                "customerId=" + customerId + ", " +
                "quantity=" + quantity + ", " +
                "productCode=" + productCode + ']';
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }
}
