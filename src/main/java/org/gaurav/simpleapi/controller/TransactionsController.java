package org.gaurav.simpleapi.controller;

import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import org.gaurav.simpleapi.model.dto.RequestDto;
import org.gaurav.simpleapi.model.dto.TransactionResponseDto;
import org.gaurav.simpleapi.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionsController {

    @Autowired
    TransactionServiceImpl transactionService;

    @PostMapping("/transactions")
    public String postTransaction(@RequestBody @Valid RequestDto requestDto) {

        transactionService.processTransaction(requestDto);


        return "OK";
    }

    @GetMapping("/transactions/customers/{customerId}")
    public TransactionResponseDto getTransactionsForCustomerId(@PathVariable @Nonnull Integer customerId) {
        return transactionService.getTransactionsForCustomerId(customerId);
    }

    @GetMapping("/transactions/products/{productCode}")
    public TransactionResponseDto getTransactionsForProductCode(@PathVariable @Nonnull String productCode) {
        return transactionService.getTransactionsForProductId(productCode);
    }

    @GetMapping("/transactions/locations/{location}")
    public TransactionResponseDto getTransactionsForLocation(@PathVariable @Nonnull String location) {
        return transactionService.getTransactionsForLocation(location);
    }


}
