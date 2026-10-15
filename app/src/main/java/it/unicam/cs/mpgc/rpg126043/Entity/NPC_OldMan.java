package it.unicam.cs.mpgc.rpg126043.Entity;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity {
    public NPC_OldMan(GamePanel gP){
        super(gP);
        directions="down";
        speed = 1;

        getOldManImages();
        setDialogue();
    }

    public void getOldManImages(){
        up1=setup("/NPC/oldman_up_1", gP.tileSize, gP.tileSize);
        up2=setup("/NPC/oldman_up_2", gP.tileSize, gP.tileSize);
        down1=setup("/NPC/oldman_down_1", gP.tileSize, gP.tileSize);
        down2=setup("/NPC/oldman_down_2", gP.tileSize, gP.tileSize);
        left1=setup("/NPC/oldman_left_1", gP.tileSize, gP.tileSize);
        left2=setup("/NPC/oldman_left_2", gP.tileSize, gP.tileSize);
        right1=setup("/NPC/oldman_right_1", gP.tileSize, gP.tileSize);
        right2=setup("/NPC/oldman_right_2", gP.tileSize, gP.tileSize);
    }
    public void setAction(){
        actionLockCounter++;

        if(actionLockCounter==120){

            Random rand = new Random();
            int i = rand.nextInt(100) + 1; // num from 1 to 100

            if(i <= 25 ){
                directions="up";
            }
            if(i > 25 && i <= 50 ){
                directions="down";
            }
            if(i > 50 && i <= 75 ){
                directions="left";
            }
            if(i > 75){
                directions="right";
            }
            actionLockCounter=0;
        }

    }
    public void setDialogue(){
        dialogue[0] = "Hello, adventurer...";
        dialogue[1] = "Thank you for coming here...";
        dialogue[2] = "this place is infested with slimes...";
        dialogue[3] = "Please defeat them all...";
    }
    public void speak() {
        //For character specific dialogues

        super.speak();
    }


}
