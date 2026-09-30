package it.unicam.cs.mpgc.rpg126043.Main;

import it.unicam.cs.mpgc.rpg126043.Entity.Entity;

public class CollisionChecker {
    GamePanel gP;
    public CollisionChecker(GamePanel gP){
        this.gP=gP;
    }
    public void checkTile(Entity e){

        int entityLeftWorldX = e.worldX +  e.solidArea.x;
        int entityRightWorldX = e.worldX + e.solidArea.x +  e.solidArea.width;
        int entityTopWorldY = e.worldY + e.solidArea.y;
        int entityBottomWorldY = e.worldY + e.solidArea.y + e.solidArea.height;

        int entityLeftCol = entityLeftWorldX/ gP.tilesize;
        int entityRightCol = entityRightWorldX/ gP.tilesize;
        int entityTopRow = entityTopWorldY/ gP.tilesize;
        int entityBottomRow = entityBottomWorldY/ gP.tilesize;

        int tileNum1,tileNum2;
        switch(e.directions){
            case "up":
                entityTopRow=(entityTopWorldY - e.speed)/ gP.tilesize;
                tileNum1= gP.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2= gP.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gP.tileM.tile[tileNum1].collision || gP.tileM.tile[tileNum2].collision){
                    e.colllisionOn=true;
                }
                break;
            case "down":
                entityBottomRow=(entityBottomWorldY + e.speed)/ gP.tilesize;
                tileNum1= gP.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2= gP.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gP.tileM.tile[tileNum1].collision || gP.tileM.tile[tileNum2].collision){
                    e.colllisionOn=true;
                }
                break;
            case "left":
                entityLeftCol=(entityLeftWorldX - e.speed)/ gP.tilesize;
                tileNum1= gP.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2= gP.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gP.tileM.tile[tileNum1].collision || gP.tileM.tile[tileNum2].collision){
                    e.colllisionOn=true;
                }
                break;
            case "right":
                entityRightCol=(entityRightWorldX + e.speed)/ gP.tilesize;
                tileNum1= gP.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2= gP.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gP.tileM.tile[tileNum1].collision || gP.tileM.tile[tileNum2].collision){
                    e.colllisionOn=true;
                }
                break;
        }
    }
}
