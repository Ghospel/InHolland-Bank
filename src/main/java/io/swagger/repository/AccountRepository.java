package io.swagger.repository;

import io.swagger.model.Account;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

}
