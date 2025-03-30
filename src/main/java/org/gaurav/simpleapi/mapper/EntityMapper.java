package org.gaurav.simpleapi.mapper;


import org.gaurav.simpleapi.model.StatusType;
import org.gaurav.simpleapi.model.dto.RequestDto;
import org.gaurav.simpleapi.model.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    public Transaction requestToTransactionMapper(RequestDto requestDto) {
        Transaction t =  new Transaction(requestDto.getTransactionTime(),
                requestDto.getCustomerId(),
                requestDto.getQuantity(),
                requestDto.getProductCode());

        return t;

    }
}
