package io.swagger;

import io.swagger.model.Role;
import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    @Autowired
    private UserRepository repo;

    private String username;
    private String password;
    Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User byUserByName) {
        this.username = byUserByName.getUsername();
        this.password = byUserByName.getPassword();



        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();

        for(Role role : byUserByName.getRoles()){
            auths.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
        }
        this.authorities = auths;

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                        SecurityContextHolder.getContext().getAuthentication().getCredentials(),
                        auths)
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
