/*
 */
package com.shops.dao;

import com.shops.entities.DislikedShop;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Abdessamad
 */
@Repository
public interface DislikedShopRepository extends MongoRepository<DislikedShop, String>{
    
    /**
     * Querying the disliked shops at a time greater than the time given,
     * By a user
     * @param username
     * @param time
     * @return 
     */
    @Query(value = "{ disliked_at: {$gt: ?1}, username: ?0}")
    public List<DislikedShop> findDislikedShops(String username, LocalDateTime time);
}
