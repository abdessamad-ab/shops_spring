/*

 */
package com.shops.entities;

/**
 *
 * @author Abdessamad
 */
class Location {
    private String type;
    private float[] coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(float[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "coordinates: "+coordinates[0]+", "+coordinates[1];
    }
    
    
}
