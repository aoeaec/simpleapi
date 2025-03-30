package org.gaurav.simpleapi.model.dto;

import java.util.Date;

public record TransactionDto(Date transactionTime,
                             Integer customerId,
                             Integer quantity,
                             String productCode,
                             Integer cost) { }
