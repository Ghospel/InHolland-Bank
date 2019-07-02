package io.swagger.model;

import javax.persistence.*;
import java.util.List;

import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User extends BaseModel{

    private String username;
    private String password;
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Role> roles;
    private boolean active;
}
