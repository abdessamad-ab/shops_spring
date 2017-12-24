/*
 */
package com.shops.entities;

import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 *
 * @author Abdessamad
 */
@Document(collection = "disliked_shops")
public class DislikedShop implements Serializable{
    @Id
    private String id;
    private String username;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date disliked_at;
    private Shop shop;

    public DislikedShop(String username, Shop shop) {
        this.disliked_at = new Date();
        this.username = username;
        this.shop = shop;
    }

    public String getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDisliked_at() {
        return disliked_at;
    }

    public void setDisliked_at(Date disliked_at) {
        this.disliked_at = disliked_at;
    }
    
    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
    
}
