/*
 */
package com.shops.web;

import com.shops.dao.DislikedShopRepository;
import com.shops.dao.ShopRepository;
import com.shops.dao.UserRepository;
import com.shops.entities.Shop;
import com.shops.entities.DislikedShop;
import com.shops.entities.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DislikedShopRepository dislikedShopRepository;
    
    /**
     * Route for all the shops sorted by distance, based on a position given by
     * longitude and latitude
     *
     * @param longitude longitude
     * @param latitude latitude
     * @return
     */
    @GetMapping(value = "/allNearby/{longitude}/{latitude}")
    public List<Shop> allNearByShops(@PathVariable double longitude, @PathVariable double latitude) {
        return shopRepository.nearByShops(longitude, latitude);
    }

    /**
     * Route for the nearby shops, excluding liked shops and disliked ones by
     * user less than 2 hours a go, sorted by distance, based on a position
     * given by longitude and latitude
     *
     * @param longitude longitude
     * @param latitude latitude
     * @param username username
     * @return
     */
    @GetMapping(value = "/nearby/{longitude}/{latitude}/{username}")
    public List<Shop> nearByShopsByUser(@PathVariable double longitude, @PathVariable double latitude, @PathVariable String username) {
        List<Shop> nearByShops = this.nearByShopsDislikedExcluded(longitude, latitude, username);

        return nearByShops;
    }

    /**
     * Route for the preferred shops by user
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/likedShops/{username}")
    public List<Shop> likedShops(@PathVariable String username) {
        List<Shop> shops = (userRepository.findByUsername(username)).getLikedShops();
        return shops;
    }

    /**
     * Route to add a new preferred shops by user
     *
     * @param shopId
     * @param username
     * @return
     */
    @GetMapping(value = "/likeShop/{shopId}/{username}")
    public List<Shop> likeShop(@PathVariable String shopId, @PathVariable String username) {
        User user = userRepository.findByUsername(username);
        List<Shop> likedShops = user.getLikedShops();
        Shop shopToLike = shopRepository.findOne(shopId);
        /**
         * the likedShops list has not changed
         */
        if (shopToLike == null || likedShops.contains(shopToLike)) {
            return likedShops;
        }

        /**
         * add the new shop the user's likedShops and update the user
         */
        user.getLikedShops().add(shopToLike);
        userRepository.save(user);
        return user.getLikedShops();
    }

    /**
     * Route to remove a shop from preferred shops by user
     *
     * @param shopId
     * @param username
     * @return
     */
    @GetMapping(value = "/removeLikedShop/{shopId}/{username}")
    public List<Shop> removeLikeShop(@PathVariable String shopId, @PathVariable String username) {
        User user = userRepository.findByUsername(username);
        Shop shopToRemove = shopRepository.findOne(shopId);

        /**
         * the likedShops list has not changed
         */
        if (!user.getLikedShops().contains(shopToRemove)) {
            return user.getLikedShops();
        }
        /**
         * Remove the shop from the user's likedShops and update the user
         */
        user.getLikedShops().remove(shopToRemove);
        userRepository.save(user);
        return user.getLikedShops();
    }
    
    /**
     * Route to dislike a shop
     *
     * @param shopId
     * @param username
     * @return
     */
    @GetMapping(value = "/dislikeShop/{shopId}/{username}")
    public Shop dislikeShop(@PathVariable String shopId, @PathVariable String username) {
        Shop shopToDislike = shopRepository.findOne(shopId);
        DislikedShop dislikedShopByUser = dislikedShopRepository.findByUsernameAndShop(username, shopToDislike);
        List<DislikedShop> listDislikedShops = dislikedShopRepository.findByUsername(username);
        
        System.out.println("dislikedShop: "+ dislikedShopByUser);
        /**
         * Insert a new dislikedShop in DB
         */
        if (!listDislikedShops.contains(dislikedShopByUser)) {
            System.out.println("in if");
            dislikedShopRepository.insert(new DislikedShop(username,shopToDislike));
            return shopToDislike;
        }
        
        return null;
    }

    private List<Shop> nearByShopsDislikedExcluded(double longitude, double latitude, String username) {
        List<Shop> nearByShops = shopRepository.nearByShops(longitude, latitude);
        List<Shop> likedShops = userRepository.findByUsername(username).getLikedShops();
        List<DislikedShop> dislikedShops = dislikedShopRepository.findDislikedShops(username, LocalDateTime.now().minusHours(2));

        /**
         * Extracting a list of shops from the DislikedShop list without the
         * username and disliked_at attributes
         */
        List<Shop> tempArray = dislikedShops.stream().map(shop -> {
            return shop.getShop();
        }).collect(Collectors.toList());

        /**
         * removing the liked and disliked shops from the nearby shops
         */
        nearByShops.removeAll(tempArray);
        nearByShops.removeAll(likedShops);
        
        return nearByShops;
    }
    
}
