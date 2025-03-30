package org.gaurav.simpleapi.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.gaurav.simpleapi.model.StatusType;

@Entity
public class Product {
@Id
    String productCode;
    int cost;
    StatusType statusType;
}
