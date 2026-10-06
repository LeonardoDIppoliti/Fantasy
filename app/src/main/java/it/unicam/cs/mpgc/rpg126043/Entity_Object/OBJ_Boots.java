package it.unicam.cs.mpgc.rpg126043.Entity_Object;

import it.unicam.cs.mpgc.rpg126043.Entity.Entity;
import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;


public class OBJ_Boots extends Entity {
    public OBJ_Boots(GamePanel gP) {
        super(gP);
        name="Boots";
        down1 = setup("/Objects/boots", gP.tilesize, gP.tilesize);

    }
}
