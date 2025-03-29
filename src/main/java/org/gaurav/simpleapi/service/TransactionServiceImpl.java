package org.gaurav.simpleapi.service;

import org.gaurav.simpleapi.model.Transaction;
import org.gaurav.simpleapi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionServiceImpl {

    @Autowired
    TransactionRepository transactionRepository;
    public void processTransaction(Transaction transactionRequest){
    transactionRepository.save(transactionRequest);

    }
}
