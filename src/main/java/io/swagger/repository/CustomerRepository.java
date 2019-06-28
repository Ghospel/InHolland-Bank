package io.swagger.repository;

import io.swagger.model.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
