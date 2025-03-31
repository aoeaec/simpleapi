package org.gaurav.simpleapi.repository;

import org.gaurav.simpleapi.model.entity.Transaction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS,scripts = {"/data_setup01.sql"})
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS , scripts = {"/delete_transactions_table.sql"})
class TransactionRepositoryTest {

    @Autowired
    TransactionRepository transactionRepository;


    private static Transaction getTransaction(int customerId, int cost, String productCode, String location) {
        Transaction transaction = new Transaction();
        transaction.setTransactionTime(new Date());
        transaction.setCustomerId(10001);
        transaction.setCost(12);
        transaction.setProductCode("PRODUCT_001");
        transaction.setLocation("Australia");
        return transaction;
    }

    private static Transaction getTransaction() {
        Transaction transaction = new Transaction();
        transaction.setTransactionTime(new Date());
        transaction.setCustomerId(10001);
        transaction.setCost(12);
        transaction.setProductCode("PRODUCT_001");
        transaction.setLocation("Australia");
        return transaction;
    }

    @ParameterizedTest
    @MethodSource("dataForFindByCustomerId")
    void findByCustomerId(int customerId, int count) {
        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);
        assertEquals(count, transactions.size());

    }


    @ParameterizedTest
    @MethodSource("dataForFindByProductCode")
    void findByProductCode(String productCode, int count) {
        List<Transaction> transactions = transactionRepository.findByProductCode(productCode);
        assertEquals(count, transactions.size());
    }

    @ParameterizedTest
    @MethodSource("dataForFindByLocation")
    void findByLocation(String country, int count) {
        List<Transaction> transactions = transactionRepository.findByLocation(country);
        assertEquals(count, transactions.size());
    }


    private static Stream<Arguments> dataForFindByCustomerId() {
        return Stream.of(
                Arguments.of(10001, 3),
                Arguments.of(10002, 7),
                Arguments.of(10003, 3),
                Arguments.of(10004, 3),
                Arguments.of(10005, 3),
                Arguments.of(10006, 0)
        );
    }

        private static Stream<Arguments> dataForFindByProductCode() {
            return Stream.of(
                    Arguments.of("PRODUCT_001", 3),
                    Arguments.of("PRODUCT_002", 4),
                    Arguments.of("PRODUCT_003", 4),
                    Arguments.of("PRODUCT_004", 4),
                    Arguments.of("PRODUCT_005", 4),
                    Arguments.of("PRODUCT_006", 0) //this code does not exists
            );
    }

    private static Stream<Arguments> dataForFindByLocation() {
        return Stream.of(
                Arguments.of("Australia", 8),
                Arguments.of("US", 11),
                Arguments.of("RANDOM_LOCATION", 0)
        );
    }
}