package it.unicam.cs.mpgc.rpg126043.Main;

import it.unicam.cs.mpgc.rpg126043.Entity.Direction;
import it.unicam.cs.mpgc.rpg126043.UI.UI;

import java.awt.*;

public class EventHandler {
    GamePanel gP;
    EventRect[][] eventRect;
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;


    public EventHandler (GamePanel gP) {
        this.gP = gP;

        eventRect=new EventRect[gP.maxWorldCol][gP.maxWorldRow];
        int col = 0;
        int row = 0;
        while (col < gP.maxWorldCol && row < gP.maxWorldRow) {
            eventRect[row][col]=new EventRect();

            eventRect[row][col].x=23;
            eventRect[row][col].y=23;
            eventRect[row][col].width=2;
            eventRect[row][col].height=2;
            eventRect[row][col].eventRectDefaultX=eventRect[row][col].x;
            eventRect[row][col].eventRectDefaultY=eventRect[row][col].y;

            col++;
            if(col==gP.maxWorldCol) {
                col = 0;
                row++;
            }
        }

    }
    public void checkEvent(){

        //CHECK IF PLAYER IS DISTANT FROM LAST EVENT
        int xDistance = Math.abs(gP.player.getWorldX()-previousEventX);
        int yDistance = Math.abs(gP.player.getWorldY()-previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance>gP.getTileSize()){
            canTouchEvent=true;
        }

        if(canTouchEvent){
            if(hit(23,7,Direction.UP)){
                healingPool(23,7);
            }
        }

    }
    //Event replays after player move away from it
    public boolean hit(int col, int row, Direction reqDirection){
        boolean hit=false;

        gP.player.getSolidArea().x = gP.player.getWorldX() + gP.player.getSolidArea().x;
        gP.player.getSolidArea().y = gP.player.getWorldY() + gP.player.getSolidArea().y;
        eventRect[col][row].x = col * gP.getTileSize() + eventRect[col][row].x;
        eventRect[col][row].y = row * gP.getTileSize() + eventRect[col][row].y;

        if(gP.player.getSolidArea().intersects(eventRect[col][row]) &&
                !eventRect[col][row].eventDone){
            if(gP.player.getDirection() == reqDirection){
                hit=true;

                previousEventX= gP.player.getWorldX();
                previousEventY= gP.player.getWorldY();
            }
        }
        //RESET
        gP.player.getSolidArea().x= gP.player.getSolidAreaDefaultX();
        gP.player.getSolidArea().y= gP.player.getSolidAreaDefaultY();
        eventRect[col][row].x=eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y=eventRect[col][row].eventRectDefaultY;

        return hit;
    }
    public void healingPool(int col,int row){
        if(gP.keyH.isEnterPressed()){
            gP.player.cancelAttack();
            gP.getDialogueScreen().setCurrentDialogue("You drink the water \n Your wounds recovered"+"\n (progress has been saved)");
            gP.ui.setGameStateIndex(UI.GameState.dialogueScreen.ordinal());
            gP.player.setCurrentLife(gP.player.getMaxLife());
            gP.saveLoad.save();

        }
    }

}
