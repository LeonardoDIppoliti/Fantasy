package it.unicam.cs.mpgc.rpg126043.Main;

import it.unicam.cs.mpgc.rpg126043.Entity.NPC_OldMan;
import it.unicam.cs.mpgc.rpg126043.Entity_Object.OBJ_Chest;
import it.unicam.cs.mpgc.rpg126043.Entity_Object.OBJ_Door;

public class AssetSetter {
    GamePanel gP;
    public AssetSetter(GamePanel gP) {
        this.gP = gP;
    }
    public void setObject(){
        gP.obj[0] = new OBJ_Door(gP);
        gP.obj[0].worldX = gP.tilesize*21;
        gP.obj[0].worldY = gP.tilesize*22;

        gP.obj[1] = new OBJ_Chest(gP);
        gP.obj[1].worldX = gP.tilesize*23;
        gP.obj[1].worldY = gP.tilesize*25;
    }

    public void setNPC(){
        gP.npc[0] = new NPC_OldMan(gP);
        gP.npc[0].worldX = gP.tilesize*21;
        gP.npc[0].worldY = gP.tilesize*21;

        gP.npc[1] = new NPC_OldMan(gP);
        gP.npc[1].worldX = gP.tilesize*11;
        gP.npc[1].worldY = gP.tilesize*21;

        gP.npc[2] = new NPC_OldMan(gP);
        gP.npc[2].worldX = gP.tilesize*31;
        gP.npc[2].worldY = gP.tilesize*21;

        gP.npc[3] = new NPC_OldMan(gP);
        gP.npc[3].worldX = gP.tilesize*21;
        gP.npc[3].worldY = gP.tilesize*11;

        gP.npc[4] = new NPC_OldMan(gP);
        gP.npc[4].worldX = gP.tilesize*21;
        gP.npc[4].worldY = gP.tilesize*31;
    }
}
