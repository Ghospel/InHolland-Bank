package io.swagger;

import io.swagger.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
    public CustomUserDetails(User userByName) {
    }
}
