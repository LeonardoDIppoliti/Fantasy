package it.unicam.cs.mpgc.rpg126043.Object;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;
import it.unicam.cs.mpgc.rpg126043.Main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision=false;
    public int worldX,worldY;
    public Rectangle solidArea =new Rectangle(0,0,48,48);
    public int solidAreaDefaultX =0;
    public int solidAreaDefaultY =0;
    UtilityTool uTool=new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gP){

        int screenX= worldX - gP.player.worldX + gP.player.screenX;
        int screenY= worldY - gP.player.worldY + gP.player.screenY;

        if(worldX + gP.tilesize >gP.player.worldX- gP.player.screenX &&
                worldX -gP.tilesize < gP.player.worldX+gP.player.screenX &&
                worldY +gP.tilesize >gP.player.worldY- gP.player.screenY &&
                worldY -gP.tilesize <gP.player.worldY + gP.player.screenY
        ){
            g2.drawImage(image,screenX,screenY,gP.tilesize, gP.tilesize, null);
        }
    }
}
