package it.unicam.cs.mpgc.rpg126043.Entity_Object;

import it.unicam.cs.mpgc.rpg126043.Entity.Entity;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;



public class OBJ_Heart extends Entity {
    public OBJ_Heart(GamePanel gP) {
        super(gP);
        name="Heart";
            image = setup("/Objects/heart_full", gP.tilesize, gP.tilesize);
            image2 = setup("/Objects/heart_half", gP.tilesize, gP.tilesize);
            image3 = setup("/Objects/heart_blank", gP.tilesize, gP.tilesize);

    }
}
