package org.gaurav.simpleapi.model;

import lombok.Data;

import java.util.Date;


public record Transaction(Date transactionTime,
                          Integer customerId,
                          Integer quantity,
                          String productCode){}
