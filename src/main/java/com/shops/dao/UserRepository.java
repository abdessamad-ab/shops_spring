/*
 */
package com.shops.dao;

import com.shops.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Abdessamad
 */
@Repository
public interface UserRepository extends MongoRepository<User, String>{
    public User findByUsername(String username);
    public User findByEmail(String email);
    public User findByEmailAndPassword(String email, String password);
    public User findByEmailOrUsername(String email, String username);
    
}
