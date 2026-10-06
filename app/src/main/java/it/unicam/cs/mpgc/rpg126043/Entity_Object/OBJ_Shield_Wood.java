package it.unicam.cs.mpgc.rpg126043.Entity_Object;

import it.unicam.cs.mpgc.rpg126043.Entity.Entity;
import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

public class OBJ_Shield_Wood extends Entity {
    public OBJ_Shield_Wood(GamePanel gP) {
        super(gP);
        name="Wood Shield";
        right1=setup("/Objects/shield_wood",gP.tilesize,gP.tilesize);
        defenseValue=1;
    }
}
