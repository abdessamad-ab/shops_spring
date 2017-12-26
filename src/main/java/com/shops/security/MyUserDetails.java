/*
 */
package com.shops.security;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Abdessamad
 * 
 * The class that spring security uses as a template for the user
 */
public class MyUserDetails implements UserDetails{
    private String email;
    private String password;
    private List<GrantedAuthority> grantedAuthorities;

    public MyUserDetails(String email, String password, List<GrantedAuthority> grantedAuthorities) {
        this.email = email;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return email;
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
