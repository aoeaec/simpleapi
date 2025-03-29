package org.gaurav.simpleapi.model.entity;


import jakarta.persistence.Entity;
import org.gaurav.simpleapi.model.StatusType;

@Entity
public class Product {

    String productCode;
    int cost;
    StatusType statusType;
}
