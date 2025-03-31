package org.gaurav.simpleapi.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransactionResponseDto {

    public static final String NO_RECORDS_FOUND = "No records found";
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int totalCost;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int totalCount;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;
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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getMessage() {
        return ObjectUtils.isEmpty(transactionsList) ? NO_RECORDS_FOUND : null;
    }

}
