package com.kronae.kixel.position;

public class Size {
    private int width;
    private int height;
    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }
    // SET
    public Size setWidth(int width) {
        this.width = width;
        return this;
    }
    public Size setHeight(int height) {
        this.height = height;
        return this;
    }
    // GET
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
