package com.kronae.kixel.pixel;

import org.jetbrains.annotations.Range;

public class RGB {
    private @Range(from=0, to=255) int r;
    private @Range(from=0, to=255) int g;
    private @Range(from=0, to=255) int b;
    protected RGB() {
    }
    public RGB( int rawValue) {
        r = (rawValue & 0xff0000) >>  16;
        g = (rawValue & 0xff00  ) >>   8;
        b = (rawValue           ) & 0xff;
    }
    public RGB(@Range(from=0, to=255) int r, @Range(from=0, to=255) int g, @Range(from=0, to=255) int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    // SET
    public RGB setRed(@Range(from=0, to=255) int r) {
        this.r = r;
        return this;
    }
    public RGB setGreen(@Range(from=0, to=255) int g) {
        this.g = g;
        return this;
    }
    public RGB setBlue(@Range(from=0, to=255) int b) {
        this.b = b;
        return this;
    }

    // GET
    public @Range(from=0, to=255) int getRed() {
        return r;
    }
    public @Range(from=0, to=255) int getGreen() {
        return g;
    }
    public @Range(from=0, to=255) int getBlue() {
        return b;
    }
    public int getRawValue() {
        return ((0xFF) << 24) |
               ((r & 0xFF) << 16) |
               ((g & 0xFF) << 8)  |
               ((b & 0xFF));
    }
    // AS
    public RGBA asRGBA() {
        return new RGBA(r, g, b, 255);
    }
    public RGBA asRGBA(@Range(from=0, to=255) int a) {
        return new RGBA(r, g, b, a);
    }
    public static final RGB WHITE = new RGB(255,255,255);
    public static final RGB BLACK = new RGB(0,0,0);
}
