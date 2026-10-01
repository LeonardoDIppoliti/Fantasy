package it.unicam.cs.mpgc.rpg126043.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {


    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImagine = new BufferedImage(width, height, original.getType());
        Graphics2D g2d = scaledImagine.createGraphics();
        g2d.drawImage(original, 0, 0, width, height, null);

        return scaledImagine;
    }
}
