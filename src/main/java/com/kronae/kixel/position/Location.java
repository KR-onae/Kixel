package com.kronae.kixel.position;

public class Location {
    private int x;
    private int y;
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    // SET
    public Location setX(int x) {
        this.x = x;
        return this;
    }
    public Location setY(int y) {
        this.y = y;
        return this;
    }
    // GET
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
