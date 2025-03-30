package org.gaurav.simpleapi.repository;


import org.gaurav.simpleapi.model.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String> {

    List<Transaction> findByCustomerId(Integer customerId);

    List<Transaction> findByProductCode(String productId);

    List<Transaction> findByLocation(String location);
}
