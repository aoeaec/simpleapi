package org.gaurav.simpleapi.model.entity;

import jakarta.persistence.*;

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
    private final String transactionTime;
    private final Integer customerId;
    private final Integer quantity;
    private final String productCode;

    public Transaction(String transactionTime,
                       Integer customerId,
                       Integer quantity,
                       String productCode) {
        this.transactionTime = transactionTime;
        this.customerId = customerId;
        this.quantity = quantity;
        this.productCode = productCode;
    }

    public String transactionTime() {
        return transactionTime;
    }

    public Integer customerId() {
        return customerId;
    }

    public Integer quantity() {
        return quantity;
    }

    public String productCode() {
        return productCode;
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
}
