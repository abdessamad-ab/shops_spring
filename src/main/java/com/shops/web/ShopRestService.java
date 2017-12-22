/*
 */
package com.shops.web;

import com.shops.dao.ShopRepository;
import com.shops.entities.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Abdessamad
 */
@RestController
@RequestMapping(value = "/shops")
public class ShopRestService {
    
    @Autowired
    private ShopRepository shopRepository;
    
    /**
     * Route for the shops sorted by distance, based on
     * a position given by longitude and latitude
     * @param longitude longitude
     * @param latitude latitude
     * @param page the number of page to query
     * @param pageSize the number of items per page
     * @return 
     */
    @GetMapping(value = "/nearby/{longitude}/{latitude}")
    public Page<Shop> nearByShops(@PathVariable double longitude, @PathVariable double latitude, 
            @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "pageSize", defaultValue = "8") int pageSize){
        System.out.println("pageSize:"+ pageSize+"page:"+ page);
        return shopRepository.nearByShops(longitude, latitude, new PageRequest(page, pageSize));
    }
    
}
