package it.unicam.cs.mpgc.rpg126043.Main;

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
        int xDistance = Math.abs(gP.player.worldX-previousEventX);
        int yDistance = Math.abs(gP.player.worldY-previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance>gP.tilesize){
            canTouchEvent=true;
        }

        if(canTouchEvent){
            if(hit(23,15,"up")){
                damageTrap(23,15,gP.dialogueState);
            }
            if(hit(23,7,"up")){
                healingPool(23,7,gP.dialogueState);
            }
        }

    }

    public boolean hit(int col,int row,String reqDirection){
        boolean hit=false;

        gP.player.solidArea.x = gP.player.worldX + gP.player.solidArea.x;
        gP.player.solidArea.y = gP.player.worldY + gP.player.solidArea.y;
        eventRect[col][row].x = col * gP.tilesize + eventRect[col][row].x;
        eventRect[col][row].y = row * gP.tilesize + eventRect[col][row].y;

        if(gP.player.solidArea.intersects(eventRect[col][row]) &&
        eventRect[col][row].eventDone == false){
            if(gP.player.directions.contentEquals(reqDirection) ||reqDirection.equals("any")){
                hit=true;

                previousEventX= gP.player.worldX;
                previousEventY= gP.player.worldY;
            }
        }
        //RESET
        gP.player.solidArea.x= gP.player.solidAreaDefaultX;
        gP.player.solidArea.y= gP.player.solidAreaDefaultY;
        eventRect[col][row].x=eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y=eventRect[col][row].eventRectDefaultY;

        return hit;
    }
    public void damageTrap(int col,int row,int gameState){
        gP.gameState=gameState;
        gP.ui.currentDialogue = "You got hit by a trap";
        gP.player.currentLife -= 1;
        //eventRect[col][row].eventDone=true;
        canTouchEvent=false;
    }
    public void healingPool(int col,int row,int gameState){
        if(gP.keyH.enterPressed){

            gP.gameState=gameState;
            gP.ui.currentDialogue = "You drink the water \n your life has been recovered";
            gP.player.currentLife = gP.player.maxLife;


        }
    }

}
