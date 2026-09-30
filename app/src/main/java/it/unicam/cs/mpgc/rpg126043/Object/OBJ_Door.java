package it.unicam.cs.mpgc.rpg126043.Object;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject{
    public OBJ_Door() {

        name="Door";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/door.png"));

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
