package it.unicam.cs.mpgc.rpg126043.Entity_Object;

import it.unicam.cs.mpgc.rpg126043.Entity.Entity;
import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

public class OBJ_Chest extends Entity {
    public OBJ_Chest(GamePanel gP) {
       super(gP);
        name="Chest";
        down1 = setup("/Objects/chest", gP.tilesize, gP.tilesize);

    }
}
