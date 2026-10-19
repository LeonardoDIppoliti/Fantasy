package it.unicam.cs.mpgc.rpg126043.Entity;

import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class SpriteSet {
    private final Map<Direction, BufferedImage[]> frames = new EnumMap<>(Direction.class);

    public void set(Direction d,BufferedImage bI1,BufferedImage bI2){
        frames.put(d,new BufferedImage[]{bI1,bI2});
    }

    public void setAll(BufferedImage bI1,BufferedImage bI2){
        for(Direction d : Direction.values()) set(d,bI1,bI2);
    }

    public BufferedImage get(Direction d,int frameNum){
        return frames.get(d)[frameNum-1];
    }
}
