package it.unicam.cs.mpgc.rpg126043.resource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
/** Loads and scales images from resources, with clear errors if the file is missing. */
public class SpriteLoader {
    private static final String EXTENSION =".png";

    private SpriteLoader(){}

    public static BufferedImage loadImage(String path, int width, int height) {
        String filePath = path + EXTENSION;
        try (InputStream inStream = SpriteLoader.class.getResourceAsStream(filePath);) {
            if (inStream == null) {
                throw new IllegalStateException("SPRITE NOT FOUND " + filePath);
            }
            BufferedImage image = ImageIO.read(inStream);
            if (image == null) {
                throw new IllegalStateException("IMAGE NOT VALID " + filePath);
            }
            return scaleImage(image, width, height);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }


    public static BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImagine = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImagine.createGraphics();
        try {
            g2d.drawImage(original,0,0,width,height,null);
        }finally {
            g2d.dispose();
        }
        return scaledImagine;
    }
}
