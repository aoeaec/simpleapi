package org.gaurav.simpleapi.service;

import org.gaurav.simpleapi.model.dto.RequestDto;
import org.gaurav.simpleapi.model.dto.TransactionResponseDto;

public interface TransactionService {
    void processTransaction(RequestDto requestDto);

    TransactionResponseDto getTransactionsForCustomerId(Integer customerId);

    TransactionResponseDto getTransactionsForProductId(String productCode);

    TransactionResponseDto getTransactionsForLocation(String location);
}
