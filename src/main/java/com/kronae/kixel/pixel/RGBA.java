package com.kronae.kixel.pixel;

import org.jetbrains.annotations.Range;

public class RGBA extends RGB {
    private @Range(from=0, to=255) int r;
    private @Range(from=0, to=255) int g;
    private @Range(from=0, to=255) int b;
    private @Range(from=0, to=255) int a;
    public RGBA(int rawValue) {
        super();
        r = (rawValue & 0xff0000  ) >>  16;
        g = (rawValue & 0xff00    ) >>   8;
        b = (rawValue             ) & 0xff;
        a = (rawValue & 0xff000000) >>> 24;
    }
    public RGBA(@Range(from=0, to=255) int r, @Range(from=0, to=255) int g, @Range(from=0, to=255) int b, @Range(from=0, to=255) int a) {
        super();
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    // SET
    public RGBA setRed(@Range(from=0, to=255) int r) {
        this.r = r;
        return this;
    }
    public RGBA setGreen(@Range(from=0, to=255) int g) {
        this.g = g;
        return this;
    }
    public RGBA setBlue(@Range(from=0, to=255) int b) {
        this.b = b;
        return this;
    }
    public RGBA setAlpha(@Range(from=0, to=255) int a) {
        this.a = a;
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
    public @Range(from=0, to=255) int getAlpha() {
        return a;
    }
    public int getRawValue() {
        return ((a & 0xFF) << 24) |
               ((r & 0xFF) << 16) |
               ((g & 0xFF) << 8)  |
               ((b & 0xFF));
    }
    // AS
    public RGB asRGB() {
        return new RGB(r, g, b);
    }
    public String toString() {
        return "RGBA-" + r + "-" + g + "-" + b + "-" + a;
    }
}
