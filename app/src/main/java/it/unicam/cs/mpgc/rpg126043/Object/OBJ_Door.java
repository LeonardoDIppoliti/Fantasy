package it.unicam.cs.mpgc.rpg126043.Object;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject{
    GamePanel gP;
    public OBJ_Door(GamePanel gP) {
        this.gP = gP;
        name="Door";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/door.png"));
            uTool.scaleImage(image, gP.tilesize,  gP.tilesize);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
