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
        up1=setup("/NPC/oldman_up_1", gP.tilesize, gP.tilesize);
        up2=setup("/NPC/oldman_up_2", gP.tilesize, gP.tilesize);
        down1=setup("/NPC/oldman_down_1", gP.tilesize, gP.tilesize);
        down2=setup("/NPC/oldman_down_2", gP.tilesize, gP.tilesize);
        left1=setup("/NPC/oldman_left_1", gP.tilesize, gP.tilesize);
        left2=setup("/NPC/oldman_left_2", gP.tilesize, gP.tilesize);
        right1=setup("/NPC/oldman_right_1", gP.tilesize, gP.tilesize);
        right2=setup("/NPC/oldman_right_2", gP.tilesize, gP.tilesize);
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
        dialogue[0] = "Hello, young man.";
        dialogue[1] = "So you too have come to this island...\n for its treasure.";
        dialogue[2] = "I used to be a great wizard but now...\n I'm too old for this adventure.";
        dialogue[3] = "So i can't accompany you...\n Be careful this island is dangerous and\n good luck.";
    }
    public void speak() {
        //For character specific dialogues

        super.speak();
    }


}
