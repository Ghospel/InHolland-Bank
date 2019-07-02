package io.swagger.model;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class BaseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
}
