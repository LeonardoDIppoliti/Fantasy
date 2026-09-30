package it.unicam.cs.mpgc.rpg126043.Object;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision=false;
    public int worldX,worldY;

    public void draw(Graphics2D g2, GamePanel gP){

        int screenX= worldX - gP.player.worldX + gP.player.screenX;
        int screenY= worldY - gP.player.worldY + gP.player.screenY;

        if(worldX + gP.tilesize >gP.player.worldX- gP.player.screenX &&
                worldX -gP.tilesize < gP.player.worldX+gP.player.screenX &&
                worldY +gP.tilesize >gP.player.worldY- gP.player.screenY &&
                worldY -gP.tilesize <gP.player.worldY + gP.player.screenY
        ){
            g2.drawImage(image,screenX,screenY,gP.tilesize, gP.tilesize,null);
        }
    }
}
