package it.unicam.cs.mpgc.rpg126043.Object;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import javax.imageio.ImageIO;

public class OBJ_Heart extends SuperObject{
    GamePanel gP;
    public OBJ_Heart(GamePanel gP) {
        this.gP=gP;
        name="Heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/Objects/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/Objects/heart_blank.png"));
            image = uTool.scaleImage(image,gP.tilesize,gP.tilesize);
            image2 = uTool.scaleImage(image2,gP.tilesize,gP.tilesize);
            image3 = uTool.scaleImage(image3,gP.tilesize,gP.tilesize);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
