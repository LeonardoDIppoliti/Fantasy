package it.unicam.cs.mpgc.rpg126043.Main;

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
        gP.obj[0] =new OBJ_Key(gP);
        gP.obj[0].worldX = 23* gP.tilesize ;
        gP.obj[0].worldY = 7* gP.tilesize ;
        gP.obj[1] =new OBJ_Key(gP);
        gP.obj[1].worldX = 23* gP.tilesize ;
        gP.obj[1].worldY = 40* gP.tilesize ;
        gP.obj[2] =new OBJ_Key(gP);
        gP.obj[2].worldX = 38* gP.tilesize ;
        gP.obj[2].worldY = 8* gP.tilesize ;

        gP.obj[3] =new OBJ_Door(gP);
        gP.obj[3].worldX = 10* gP.tilesize ;
        gP.obj[3].worldY = 11* gP.tilesize ;

        gP.obj[4] =new OBJ_Door(gP);
        gP.obj[4].worldX = 8* gP.tilesize ;
        gP.obj[4].worldY = 28* gP.tilesize ;


        gP.obj[5] =new OBJ_Door(gP);
        gP.obj[5].worldX = 12* gP.tilesize ;
        gP.obj[5].worldY = 22* gP.tilesize ;


        gP.obj[6] =new OBJ_Chest(gP);
        gP.obj[6].worldX = 10* gP.tilesize ;
        gP.obj[6].worldY = 7* gP.tilesize ;

        gP.obj[7] =new OBJ_Boots(gP);
        gP.obj[7].worldX = 37* gP.tilesize ;
        gP.obj[7].worldY = 42* gP.tilesize ;


    }
}
