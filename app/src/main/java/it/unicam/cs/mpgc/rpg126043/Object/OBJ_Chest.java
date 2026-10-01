package it.unicam.cs.mpgc.rpg126043.Object;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject{
    GamePanel gP;
    public OBJ_Chest(GamePanel gP) {
        this.gP = gP;
        name="Chest";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/chest.png"));
            uTool.scaleImage(image, gP.tilesize,  gP.tilesize);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
