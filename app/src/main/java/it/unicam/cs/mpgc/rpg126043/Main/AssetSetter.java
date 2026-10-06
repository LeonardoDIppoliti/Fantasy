package it.unicam.cs.mpgc.rpg126043.Main;

import it.unicam.cs.mpgc.rpg126043.Entity.NPC_OldMan;
import it.unicam.cs.mpgc.rpg126043.Entity_Object.OBJ_Chest;
import it.unicam.cs.mpgc.rpg126043.Entity_Object.OBJ_Door;
import it.unicam.cs.mpgc.rpg126043.Monster.MON_GreenSlime;

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

        gP.npc[1] = new NPC_OldMan(gP);
        gP.npc[1].worldX = gP.tilesize*23;
        gP.npc[1].worldY = gP.tilesize*35;

    }
    public void setMonster(){
        gP.monster[0] =new MON_GreenSlime(gP);
        gP.monster[0].worldX = gP.tilesize*23;
        gP.monster[0].worldY = gP.tilesize*36;

        gP.monster[1] =new MON_GreenSlime(gP);
        gP.monster[1].worldX = gP.tilesize*23;
        gP.monster[1].worldY = gP.tilesize*37;
    }
}
