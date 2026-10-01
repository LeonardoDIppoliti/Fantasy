package it.unicam.cs.mpgc.rpg126043.Object;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject{
    GamePanel gP;
    public OBJ_Key(GamePanel gP){
        this.gP=gP;
        name="Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/key.png"));
            uTool.scaleImage(image, gP.tilesize,  gP.tilesize);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
