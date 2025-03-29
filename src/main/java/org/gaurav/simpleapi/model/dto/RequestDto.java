package org.gaurav.simpleapi.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RequestDto {

    Date transactionTime;
    Integer customerId;
    Integer quantity;
    String productCode;

}
