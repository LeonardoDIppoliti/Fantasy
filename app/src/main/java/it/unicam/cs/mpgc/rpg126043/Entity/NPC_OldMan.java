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
        up1=setup("/NPC/oldman_up_1");
        up2=setup("/NPC/oldman_up_2");
        down1=setup("/NPC/oldman_down_1");
        down2=setup("/NPC/oldman_down_2");
        left1=setup("/NPC/oldman_left_1");
        left2=setup("/NPC/oldman_left_2");
        right1=setup("/NPC/oldman_right_1");
        right2=setup("/NPC/oldman_right_2");
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
            if(i > 75 && i <= 100 ){
                directions="right";
            }
            actionLockCounter=0;
        }

    }
    public void setDialogue(){
        dialogue[0] = "Hello, young man";
        dialogue[1] = "So you too have come to the island...\n to find its treasure";
        dialogue[2] = "I used to be a great wizard but now...\n i'm a bit too old for this adventure";
        dialogue[3] = "So i can't come with you...\n be careful of this island dangers.\n Good luck";
    }
    public void speak() {
        //For character specific dialogues

        super.speak();
    }


}
