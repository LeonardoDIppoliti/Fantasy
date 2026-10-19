package it.unicam.cs.mpgc.rpg126043.Main;

import it.unicam.cs.mpgc.rpg126043.Entity.Entity;

import java.awt.*;
import java.util.Random;

public class CollisionChecker {
    GamePanel gP;
    public CollisionChecker(GamePanel gP){
        this.gP=gP;
    }
    public void checkTile(Entity e){

        int entityLeftWorldX = e.getWorldX() +  e.getSolidArea().x;
        int entityRightWorldX = e.getWorldX() + e.getSolidArea().x +  e.getSolidArea().width;
        int entityTopWorldY = e.getWorldY() + e.getSolidArea().y;
        int entityBottomWorldY = e.getWorldY() + e.getSolidArea().y + e.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX / gP.getTileSize();
        int entityRightCol = entityRightWorldX / gP.getTileSize();
        int entityTopRow = entityTopWorldY / gP.getTileSize();
        int entityBottomRow = entityBottomWorldY / gP.getTileSize();

        int tileNum1,tileNum2;
        switch(e.getDirection()){
            case UP:
                entityTopRow=(entityTopWorldY - e.getSpeed())/ gP.getTileSize();
                tileNum1= gP.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2= gP.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gP.tileM.tile[tileNum1].collision || gP.tileM.tile[tileNum2].collision){
                    e.setCollisionOn(true);
                }
                break;
            case DOWN:
                entityBottomRow=(entityBottomWorldY + e.getSpeed())/ gP.getTileSize();
                tileNum1= gP.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2= gP.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gP.tileM.tile[tileNum1].collision || gP.tileM.tile[tileNum2].collision){
                    e.setCollisionOn(true);
                }
                break;
            case LEFT:
                entityLeftCol=(entityLeftWorldX - e.getSpeed())/ gP.getTileSize();
                tileNum1= gP.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2= gP.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gP.tileM.tile[tileNum1].collision || gP.tileM.tile[tileNum2].collision){
                    e.setCollisionOn(true);
                }
                break;
            case RIGHT:
                entityRightCol=(entityRightWorldX + e.getSpeed())/ gP.getTileSize();
                tileNum1= gP.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2= gP.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gP.tileM.tile[tileNum1].collision || gP.tileM.tile[tileNum2].collision){
                    e.setCollisionOn(true);
                }
                break;
        }
    }
    //Player to NPC collision
    public int checkEntity(Entity e,Entity[] target){
        int index =999;
        for(int i = 0;i<target.length;i++){

            if(target[i]!=null){

                //get entity's solid area position
                e.getSolidArea().x = e.getWorldX() + e.getSolidArea().x;
                e.getSolidArea().y = e.getWorldY() + e.getSolidArea().y;
                // get obj's solid area position
                target[i].getSolidArea().x = target[i].getWorldX() + target[i].getSolidArea().x;
                target[i].getSolidArea().y = target[i].getWorldY() + target[i].getSolidArea().y;

                e.getSolidArea().x += e.getDirection().getdX()*e.getSpeed();
                e.getSolidArea().y += e.getDirection().getdY()*e.getSpeed();

                if(e.getSolidArea().intersects(target[i].getSolidArea())){
                    if(target[i] != e){
                        e.setCollisionOn(true);
                        index=i;

                    }
                }
                e.getSolidArea().x = e.getSolidAreaDefaultX();
                e.getSolidArea().y = e.getSolidAreaDefaultY();
                target[i].getSolidArea().x = target[i].getSolidAreaDefaultX();
                target[i].getSolidArea().y = target[i].getSolidAreaDefaultY();
            }


        }
        return index;
    }
    //Entity to Player collision
    public boolean checkPlayer(Entity e){

        boolean contactPlayer = false;

            //get entity's solid area position
            e.getSolidArea().x = e.getWorldX() + e.getSolidArea().x;
            e.getSolidArea().y = e.getWorldY() + e.getSolidArea().y;
            // get player solid area position
            gP.player.getSolidArea().x = gP.player.getWorldX() + gP.player.getSolidArea().x;
            gP.player.getSolidArea().y = gP.player.getWorldY() + gP.player.getSolidArea().y;

        e.getSolidArea().x += e.getDirection().getdX() * e.getSpeed();
        e.getSolidArea().y += e.getDirection().getdY() * e.getSpeed();

        if(e.getSolidArea().intersects(gP.player.getSolidArea())){
            e.setCollisionOn(true);
            contactPlayer=true;
        }
            e.getSolidArea().x = e.getSolidAreaDefaultX();
            e.getSolidArea().y = e.getSolidAreaDefaultY();
            gP.player.getSolidArea().x = gP.player.getSolidAreaDefaultX();
            gP.player.getSolidArea().y = gP.player.getSolidAreaDefaultY();
        return contactPlayer;
    }
    public int checkArea(Rectangle area, Entity[] targets){
        for(int i = 0;i<targets.length;i++){
            Entity target = targets[i];
            if(target == null) continue;
            Rectangle box = new Rectangle(target.getWorldX()+target.getSolidArea().x,
                    target.getWorldY()+target.getSolidArea().y,
                    (int) target.getSolidArea().getWidth(),(int)  target.getSolidArea().getHeight());
            if (area.intersects(box)) {return i;}
        }
        return gP.player.getIndexMax();
    }

}
