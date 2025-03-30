package org.gaurav.simpleapi.controller;

import jakarta.validation.Valid;
import org.gaurav.simpleapi.mapper.EntityMapper;
import org.gaurav.simpleapi.model.dto.RequestDto;
import org.gaurav.simpleapi.model.entity.Transaction;
import org.gaurav.simpleapi.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionsController {

    @Autowired
    TransactionServiceImpl transactionService;

    @Autowired
    EntityMapper mapper;

    @PostMapping("/transactions")
    public String postTransaction(@RequestBody @Valid RequestDto requestDto) {
        Transaction transaction = mapper.requestToTransactionMapper(requestDto);

        transactionService.processTransaction(transaction);


        return "OK";
    }


}
