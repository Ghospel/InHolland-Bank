package io.swagger.repository;

import io.swagger.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
