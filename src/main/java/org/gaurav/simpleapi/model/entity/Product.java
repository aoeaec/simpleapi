package org.gaurav.simpleapi.model.entity;


import jakarta.persistence.*;
import org.gaurav.simpleapi.model.StatusType;

@Entity
public class Product {
@Id
@Column(name = "PRODUCT_CODE")
    String productCode;
    @Column(name = "COST", nullable = false)
    int cost;
    @Enumerated(EnumType.STRING)
    @Column(name= "STATUS" , nullable = false)
    StatusType statusType;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }
}
