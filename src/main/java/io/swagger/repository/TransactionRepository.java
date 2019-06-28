package io.swagger.repository;

import io.swagger.model.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
