package com.kronae.kixel;

import com.kronae.kixel.position.Location;
import com.kronae.kixel.position.Size;
import com.kronae.kixel.pixel.Pixel;
import com.kronae.kixel.pixel.RGB;
import com.kronae.kixel.pixel.RGBA;
import com.kronae.kixel.exception.TooFewValuesException;
import com.kronae.kixel.type.KixelSavingType;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Kixel {
    private final BufferedImage image;
    public Kixel(BufferedImage image) {
        this.image = image;
    }
    public Kixel(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }
    public Kixel(FileReader reader) throws IOException {
        image = ImageIO.read((ImageInputStream) reader);
    }
    public Kixel paint(Location location, RGB rgb) {
        image.setRGB(location.getX(), location.getY(), rgb.getRawValue());
        return this;
    }
    public Kixel paint(Location from, Location to, RGBA rgba) {
        return paint(from, new Size(to.getX() - from.getX(), to.getY() - from.getY()), rgba);
    }
    public Kixel paint(Location start, Size size, RGBA rgba) {
        for(int x = 0; x < size.getWidth(); x++) {
            for(int y = 0; y < size.getWidth(); y++) {
                image.setRGB(start.getX() + x, start.getY() + y, rgba.getRawValue());
            }
        }
        return this;
    }
    public Kixel paint(Location start, Size size, int offset, RGBA[] rgbaArray) throws TooFewValuesException {
        int w = size.getWidth();
        int h = size.getHeight();

        if(rgbaArray.length < w * h) throw new TooFewValuesException("Kixel#paint(Location, Size, int offset, RGBA[]): Too few rgb array values.");
        for(int x = 0; x < w; x++) {
            for(int y = 0; y < h; y++) {
                image.setRGB(start.getX() + x, start.getY() + y, rgbaArray[offset].getRawValue());
                offset++;
            }
        }
        return this;
    }
    public Graphics2D getGraphics() {
        return (Graphics2D) image.getGraphics();
    }
    public BufferedImage getBufferedImage() {
        return image;
    }
    public RGBA getRGBA(int x, int y) {
        return new RGBA(image.getRGB(x, y));
    }
    public RGBA getRGBA(Location location) {
        return new RGBA(image.getRGB(location.getX(), location.getY()));
    }
    public Pixel[] getPixels() {
        int w = image.getWidth();
        int h = image.getHeight();

        Pixel[] pixels = new Pixel[w * h];

        int offset = 0;
        for(int y = 0; y < h; y++) {
            for(int x = 0; x < w; x++) {
                pixels[offset] = new Pixel(new RGBA(image.getRGB(x, y)), new Location(x, y));
                offset++;
            }
        }
        return pixels;
    }
    public Kixel asRGBKixel(RGB backgroundColor) {
        for (Pixel pixel : getPixels()) {
            Location location = pixel.getLocation();
            if(pixel.getRgba().getAlpha() == 0) {
                image.setRGB(location.getX(), location.getY(), backgroundColor.getRawValue());
            } else {
                image.setRGB(location.getX(), location.getY(), pixel.getRgba().asRGB().getRawValue());
            }
        }
        return this;
    }
    public Kixel save(File outputFile, KixelSavingType savingFormat) throws IOException {
        String format = savingFormat.name().toLowerCase();
        ImageIO.write(image, format, outputFile);
        return this;
    }

    // STATIC \\
    public static Kixel read(File file) throws IOException {
        return new Kixel(ImageIO.read(file));
    }
    public static Kixel read(InputStream stream) throws IOException {
        return new Kixel(ImageIO.read(stream));
    }
}
