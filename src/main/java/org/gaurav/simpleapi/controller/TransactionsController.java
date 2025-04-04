package org.gaurav.simpleapi.controller;

import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import org.gaurav.simpleapi.model.dto.RequestDto;
import org.gaurav.simpleapi.model.dto.TransactionResponseDto;
import org.gaurav.simpleapi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionsController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/transactions")
    public ResponseEntity<Object> postTransaction(@RequestBody @Valid RequestDto requestDto) {
        transactionService.processTransaction(requestDto);
        return new ResponseEntity<>("Transaction Successful", HttpStatus.CREATED);
    }

    @GetMapping("/transactions/customers/{customerId}")
    public TransactionResponseDto getTransactionsForCustomerId(@PathVariable @Nonnull Integer customerId) {
        return transactionService.getTransactionsForCustomerId(customerId);
    }

    @GetMapping("/transactions/products/{productCode}")
    public TransactionResponseDto getTransactionsForProductCode(@PathVariable @Nonnull String productCode) {
        return transactionService.getTransactionsForProductCode(productCode);
    }

    @GetMapping("/transactions/locations/{location}")
    public TransactionResponseDto getTransactionsForLocation(@PathVariable @Nonnull String location) {
        return transactionService.getTransactionsForLocation(location);
    }


}
