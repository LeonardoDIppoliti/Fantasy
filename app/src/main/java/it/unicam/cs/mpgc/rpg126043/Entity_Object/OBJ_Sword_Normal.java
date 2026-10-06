package it.unicam.cs.mpgc.rpg126043.Entity_Object;

import it.unicam.cs.mpgc.rpg126043.Entity.Entity;
import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

public class OBJ_Sword_Normal extends Entity {
    public OBJ_Sword_Normal(GamePanel gP) {
        super(gP);

        name="Sword Normal";
       right1=setup("/Objects/sword_normal",gP.tilesize,gP.tilesize);
       attackValue=1;
    }
}
