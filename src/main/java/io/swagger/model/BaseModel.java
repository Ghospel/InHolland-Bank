package io.swagger.model;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;


@Entity
@Getter
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class BaseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
}
