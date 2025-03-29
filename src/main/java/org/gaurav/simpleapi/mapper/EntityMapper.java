package org.gaurav.simpleapi.mapper;

import org.gaurav.simpleapi.model.Transaction;
import org.gaurav.simpleapi.model.dto.RequestDto;

public class EntityMapper {

    public Transaction requestToTransactionMapper(RequestDto requestDto) {
        return new Transaction(requestDto.getTransactionTime(),
                requestDto.getCustomerId(),
                requestDto.getQuantity(),
                requestDto.getProductCode());

    }
}
