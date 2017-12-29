/*
 */
package com.shops.web;

import com.shops.dao.UserRepository;
import com.shops.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Abdessamad
 */
@RestController
@CrossOrigin(origins = "*")
public class UserRestService {
    
    @Autowired
    private UserRepository userRepository;
    
    /**
     * Authentication Route
     * @param user
     * @return 
     */
    @PostMapping(value = "/authentication")
    public User login(@RequestBody User user) {
        User userDb = this.userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if(userDb != null){
         /**
         * clear the likedShops list to loosen the user object
         */
        userDb.getLikedShops().clear();
        }
        
        return userDb;
    }
    
    /**
     * Register Route
     * @param user
     * @return 
     */
    @PostMapping(value = "/register")
    public boolean register(@RequestBody User user) {
        User userDb = this.userRepository.findByEmailOrUsername(user.getEmail(), user.getUsername());
        if(userDb != null){
            return false;
        }
        
        userRepository.insert(user);
        return true;
    }
}
