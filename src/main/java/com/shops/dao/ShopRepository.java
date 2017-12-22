package com.shops.dao;

import com.shops.entities.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Abdessamad
 */
@Repository
public interface ShopRepository extends MongoRepository<Shop, String>{

    /**
     * Method that looks for shops sorted by distance based on
     * a position given by longitude and latitude
     * @param longitude longitude
     * @param latitude latitude
     * @param pageable contains number of page and its size
     * @return Page
     */
    @Query(value = "{\"location\":\n" +
"       { $near :\n" +
"          {\n" +
"            $geometry : {\n" +
"               type : \"Point\" ,\n" +
"               coordinates : [ ?0, ?1] }\n" +
"          }\n" +
"       }}")
    public Page<Shop> nearByShops(double longitude, double latitude, Pageable pageable);
    
}
