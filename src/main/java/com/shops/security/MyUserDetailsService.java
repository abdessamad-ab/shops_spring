/*
 */
package com.shops.security;

import com.shops.dao.UserRepository;
import com.shops.entities.User;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Abdessamad
 * 
 * * The class that spring security uses to load the user
 */
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    
    /**
     * The method that spring calls to get the full user
     * @param email
     * @return
     * @throws UsernameNotFoundException 
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user!=null) {
            String password = user.getPassword();
            MyUserDetails myUserDetails = new MyUserDetails(email,password,new ArrayList<>());
            return myUserDetails;
        }
        return null;
    }
    
}
