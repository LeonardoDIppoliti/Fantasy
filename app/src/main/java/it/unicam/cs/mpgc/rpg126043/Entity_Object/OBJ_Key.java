package it.unicam.cs.mpgc.rpg126043.Entity_Object;

import it.unicam.cs.mpgc.rpg126043.Entity.Entity;
import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;



public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gP){
      super(gP);
        name="Key";
        down1 = setup("/Objects/key", gP.tilesize, gP.tilesize);

    }
}
