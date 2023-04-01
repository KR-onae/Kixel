package com.kronae.kixel.pixel;

import com.kronae.kixel.position.Location;

public class Pixel {
    private RGBA rgba;
    private Location location;
    public Pixel(RGBA rgba, Location location) {
        this.rgba = rgba;
        this.location = location;
    }
    public RGBA getRgba() {
        return rgba;
    }
    public Location getLocation() {
        return location;
    }
}
