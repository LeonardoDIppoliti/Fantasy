package it.unicam.cs.mpgc.rpg126043.Object;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject{
    public OBJ_Key(){

        name="Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/Objects/key.png"));

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
