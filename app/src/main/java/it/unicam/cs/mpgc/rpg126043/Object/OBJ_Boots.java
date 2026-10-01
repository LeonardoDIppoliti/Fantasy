package it.unicam.cs.mpgc.rpg126043.Object;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;
import it.unicam.cs.mpgc.rpg126043.Main.UtilityTool;

import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObject{
    GamePanel gP;
    public OBJ_Boots(GamePanel gP) {
        this.gP=gP;
        name="Boots";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/boots.png"));
            uTool.scaleImage(image,gP.tilesize,gP.tilesize);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
