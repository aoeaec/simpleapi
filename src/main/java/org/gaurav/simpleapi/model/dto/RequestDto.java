package org.gaurav.simpleapi.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import org.gaurav.simpleapi.validation.DateValidation;

import java.util.Date;

@Data
public class RequestDto {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateValidation(message = "Date cannot be in past")
    Date transactionTime;
    Integer customerId;
    Integer quantity;
    String productCode;

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
}
