package io.swagger.auth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Optional;

@Entity
public class ApiKey {
    @Id
    private String key;

    ApiKey apiKey(String key){
        this.key = key;
        return this;
    }
}
