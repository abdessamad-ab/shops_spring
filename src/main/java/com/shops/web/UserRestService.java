/*
 */
package com.shops.web;

import com.shops.dao.UserRepository;
import com.shops.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @GetMapping(value = "/authentication")
    public User login(@RequestParam String email, @RequestParam String password) {
        User userDb = this.userRepository.findByEmailAndPassword(email, password);
        if(userDb != null){
         /**
         * clear the likedShops list to loosen the user object
         */
        userDb.getLikedShops().clear();
        }
        
        return userDb;
    }
    
    @GetMapping(value = "/register")
    public User register(@RequestParam String email, @RequestParam String password, @RequestParam String username) {
        User userDb = this.userRepository.findByEmailAndPassword(email, password);
        if(userDb != null){
         /**
         * clear the likedShops list to loosen the user object
         */
        userDb.getLikedShops().clear();
        }
        
        return userDb;
    }
}
