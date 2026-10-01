package it.unicam.cs.mpgc.rpg126043.Main;

import it.unicam.cs.mpgc.rpg126043.Entity.NPC_OldMan;
import it.unicam.cs.mpgc.rpg126043.Object.OBJ_Boots;
import it.unicam.cs.mpgc.rpg126043.Object.OBJ_Chest;
import it.unicam.cs.mpgc.rpg126043.Object.OBJ_Door;
import it.unicam.cs.mpgc.rpg126043.Object.OBJ_Key;

public class AssetSetter {
    GamePanel gP;
    public AssetSetter(GamePanel gP) {
        this.gP = gP;
    }
    public void setObject(){

    }

    public void setNPC(){
        gP.npc[0] = new NPC_OldMan(gP);
        gP.npc[0].worldX = gP.tilesize*21;
        gP.npc[0].worldY = gP.tilesize*21;
    }
}
