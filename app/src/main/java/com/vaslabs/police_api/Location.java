package com.vaslabs.police_api;

/**
 * Created by vnicolaou on 02/11/15.
 */
public final class Location {
    private double latitude;
    private double longitude;

    private Street street;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Street getStreet() {
        return street;
    }
}
