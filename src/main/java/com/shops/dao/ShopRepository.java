package com.shops.dao;

import com.shops.entities.Shop;
import java.util.List;
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
     * Method that looks for all shops sorted by distance based on
     * a position given by longitude and latitude
     * @param longitude longitude
     * @param latitude latitude
     * @return Page
     */
    @Query(value = "{location:" +
"       { $near :" +
"          {" +
"            $geometry : {" +
"               type : 'Point' ," +
"               coordinates : [ ?0, ?1] }" +
"          }" +
"       }}")
    public List<Shop> nearByShops(double longitude, double latitude);
    
}
