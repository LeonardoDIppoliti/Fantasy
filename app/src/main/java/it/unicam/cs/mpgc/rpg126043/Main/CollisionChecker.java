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
                    e.collisionOn=true;

                }
                break;
            case "down":
                entityBottomRow=(entityBottomWorldY + e.speed)/ gP.tilesize;
                tileNum1= gP.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2= gP.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gP.tileM.tile[tileNum1].collision || gP.tileM.tile[tileNum2].collision){
                    e.collisionOn=true;
                }
                break;
            case "left":
                entityLeftCol=(entityLeftWorldX - e.speed)/ gP.tilesize;
                tileNum1= gP.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2= gP.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gP.tileM.tile[tileNum1].collision || gP.tileM.tile[tileNum2].collision){
                    e.collisionOn=true;
                }
                break;
            case "right":
                entityRightCol=(entityRightWorldX + e.speed)/ gP.tilesize;
                tileNum1= gP.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2= gP.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gP.tileM.tile[tileNum1].collision || gP.tileM.tile[tileNum2].collision){
                    e.collisionOn=true;
                }
                break;
        }
    }
    public int checkObject(Entity e,boolean player){
       int index =999;

       for(int i = 0;i<gP.obj.length;i++){

           if(gP.obj[i]!=null){

               //get entity's solid area position
               e.solidArea.x = e.worldX + e.solidArea.x;
               e.solidArea.y = e.worldY + e.solidArea.y;
               // get obj's solid area position
               gP.obj[i].solidArea.x = gP.obj[i].worldX + gP.obj[i].solidArea.x;
               gP.obj[i].solidArea.y = gP.obj[i].worldY + gP.obj[i].solidArea.y;

               switch(e.directions){
                   case "up":
                       e.solidArea.y -= e.speed;
                       break;
                   case "down":
                       e.solidArea.y += e.speed;
                       break;
                   case "left":
                       e.solidArea.x -= e.speed;
                       break;
                   case "right":
                       e.solidArea.x += e.speed;
                       break;
               }
               if(e.solidArea.intersects(gP.obj[i].solidArea)){
                   if(gP.obj[i].collision){
                       e.collisionOn=true;
                   }
                   if(player){
                       index=i;
                   }
               }
               e.solidArea.x = e.solidAreaDefaultX;
               e.solidArea.y = e.solidAreaDefaultY;
               gP.obj[i].solidArea.x = gP.obj[i].solidAreaDefaultX;
               gP.obj[i].solidArea.y = gP.obj[i].solidAreaDefaultY;
           }


       }
        return index;
    }
    //Player to NPC collision
    public int checkEntity(Entity e,Entity[] target){
        int index =999;
        for(int i = 0;i<target.length;i++){

            if(target[i]!=null){

                //get entity's solid area position
                e.solidArea.x = e.worldX + e.solidArea.x;
                e.solidArea.y = e.worldY + e.solidArea.y;
                // get obj's solid area position
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch(e.directions) {
                    case "up":
                        e.solidArea.y -= e.speed;
                        break;
                    case "down":
                        e.solidArea.y += e.speed;
                        break;
                    case "left":
                        e.solidArea.x -= e.speed;
                        break;
                    case "right":
                        e.solidArea.x += e.speed;
                        break;
                }
                if(e.solidArea.intersects(target[i].solidArea)){
                    if(target[i] != e){
                        e.collisionOn=true;
                        index=i;

                    }
                }
                e.solidArea.x = e.solidAreaDefaultX;
                e.solidArea.y = e.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }


        }
        return index;
    }
    //Entity to Player collision
    public boolean checkPlayer(Entity e){

        boolean contactPlayer = false;

            //get entity's solid area position
            e.solidArea.x = e.worldX + e.solidArea.x;
            e.solidArea.y = e.worldY + e.solidArea.y;
            // get obj's solid area position
            gP.player.solidArea.x = gP.player.worldX + gP.player.solidArea.x;
            gP.player.solidArea.y = gP.player.worldY + gP.player.solidArea.y;

            switch(e.directions){
                case "up":
                    e.solidArea.y -= e.speed;
                    break;
                case "down":
                    e.solidArea.y += e.speed;
                    break;
                case "left":
                    e.solidArea.x -= e.speed;
                    break;
                case "right":
                    e.solidArea.x += e.speed;
                    break;
            }
        if(e.solidArea.intersects(gP.player.solidArea)){
            e.collisionOn=true;
            contactPlayer=true;
        }
            e.solidArea.x = e.solidAreaDefaultX;
            e.solidArea.y = e.solidAreaDefaultY;
            gP.player.solidArea.x = gP.player.solidAreaDefaultX;
            gP.player.solidArea.y = gP.player.solidAreaDefaultY;
        return contactPlayer;
    }

}
