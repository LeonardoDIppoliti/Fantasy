package it.unicam.cs.mpgc.rpg126043.Main;

import it.unicam.cs.mpgc.rpg126043.Entity.Entity;
import it.unicam.cs.mpgc.rpg126043.Entity.NPC_OldMan;
import it.unicam.cs.mpgc.rpg126043.Monster.MON_GreenSlime;

import java.util.Arrays;

public class AssetSetter {
    GamePanel gP;
    private int i=0;
    public AssetSetter(GamePanel gP) {
        this.gP = gP;
    }

    public void setNPC(){
        Arrays.fill(gP.npc,null);
        int i=0;
        gP.npc[i] = new NPC_OldMan(gP);
        gP.npc[i].worldX = gP.tileSize*21;
        gP.npc[i].worldY = gP.tileSize*21;
    }
    public void setMonster(){
        Arrays.fill(gP.monster,null);
        i=0;
        createMonster(new MON_GreenSlime(gP),gP.tileSize*23, gP.tileSize*36);

        gP.monster[i] =new MON_GreenSlime(gP);
        gP.monster[i].worldX = gP.tileSize*20;
        gP.monster[i].worldY = gP.tileSize*37;
        i++;

        gP.monster[i] =new MON_GreenSlime(gP);
        gP.monster[i].worldX = gP.tileSize*8;
        gP.monster[i].worldY = gP.tileSize*31;
        i++;

        gP.monster[i] =new MON_GreenSlime(gP);
        gP.monster[i].worldX = gP.tileSize*10;
        gP.monster[i].worldY = gP.tileSize*31;
        i++;

        gP.monster[i] =new MON_GreenSlime(gP);
        gP.monster[i].worldX = gP.tileSize*38;
        gP.monster[i].worldY = gP.tileSize*11;
        i++;

        gP.monster[i] =new MON_GreenSlime(gP);
        gP.monster[i].worldX = gP.tileSize*35;
        gP.monster[i].worldY = gP.tileSize*7;
        i++;

        gP.monster[i] =new MON_GreenSlime(gP);
        gP.monster[i].worldX = gP.tileSize*35;
        gP.monster[i].worldY = gP.tileSize*37;
        i++;

        gP.monster[i] =new MON_GreenSlime(gP);
        gP.monster[i].worldX = gP.tileSize*35;
        gP.monster[i].worldY = gP.tileSize*33;

    }
    private void createMonster(Entity monster,int x,int y){
        gP.monster[i] = monster;
        gP.monster[i].worldX = x;
        gP.monster[i].worldY = y;
        i++;
    }
}
