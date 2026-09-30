package it.unicam.cs.mpgc.rpg126043.Main;

import it.unicam.cs.mpgc.rpg126043.Object.OBJ_Chest;
import it.unicam.cs.mpgc.rpg126043.Object.OBJ_Door;
import it.unicam.cs.mpgc.rpg126043.Object.OBJ_Key;

public class AssetSetter {
    GamePanel gP;
    public AssetSetter(GamePanel gP) {
        this.gP = gP;
    }
    public void setObject(){
        gP.obj[0] =new OBJ_Key();
        gP.obj[0].worldX = 23* gP.tilesize ;
        gP.obj[0].worldY = 7* gP.tilesize ;
        gP.obj[1] =new OBJ_Key();
        gP.obj[1].worldX = 23* gP.tilesize ;
        gP.obj[1].worldY = 40* gP.tilesize ;
        gP.obj[2] =new OBJ_Key();
        gP.obj[2].worldX = 38* gP.tilesize ;
        gP.obj[2].worldY = 8* gP.tilesize ;

        gP.obj[3] =new OBJ_Door();
        gP.obj[3].worldX = 10* gP.tilesize ;
        gP.obj[3].worldY = 11* gP.tilesize ;
        gP.obj[3].collision=true;

        gP.obj[4] =new OBJ_Door();
        gP.obj[4].worldX = 8* gP.tilesize ;
        gP.obj[4].worldY = 28* gP.tilesize ;
        gP.obj[4].collision=true;

        gP.obj[5] =new OBJ_Door();
        gP.obj[5].worldX = 12* gP.tilesize ;
        gP.obj[5].worldY = 22* gP.tilesize ;
        gP.obj[5].collision=true;

        gP.obj[6] =new OBJ_Chest();
        gP.obj[6].worldX = 10* gP.tilesize ;
        gP.obj[6].worldY = 7* gP.tilesize ;
        gP.obj[6].collision=true;

    }
}
