package org.gaurav.simpleapi.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class TransactionResponseDto {

    private int totalCost;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TransactionDto> transactionsList = new ArrayList<>();


    public List<TransactionDto> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<TransactionDto> transactionsList) {
        this.transactionsList = transactionsList;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
