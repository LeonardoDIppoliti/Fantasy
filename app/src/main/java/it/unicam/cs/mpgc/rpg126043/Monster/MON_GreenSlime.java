package it.unicam.cs.mpgc.rpg126043.Monster;

import it.unicam.cs.mpgc.rpg126043.Entity.Entity;
import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.util.Random;

public class MON_GreenSlime extends Entity {
    GamePanel gP;

    public MON_GreenSlime(GamePanel gP) {
        super(gP);
        this.type = 2;
        this.gP = gP;


        //STATS
        name="Green Slime";
        speed=1;
        maxLife=4;
        currentLife=maxLife;
        defense = 7;
        attack = 12;


        solidArea.x=3;
        solidArea.y=18;
        solidArea.width=42;
        solidArea.height=30;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;

        getImage();

    }

    public void getImage(){
        up1=setup("/Enemy/Walking_Sprites/greenslime_down_1", gP.tilesize, gP.tilesize);
        up2=setup("/Enemy/Walking_Sprites/greenslime_down_2", gP.tilesize, gP.tilesize);
        down1=setup("/Enemy/Walking_Sprites/greenslime_down_1", gP.tilesize, gP.tilesize);
        down2=setup("/Enemy/Walking_Sprites/greenslime_down_2", gP.tilesize, gP.tilesize);
        left1=setup("/Enemy/Walking_Sprites/greenslime_down_1", gP.tilesize, gP.tilesize);
        left2=setup("/Enemy/Walking_Sprites/greenslime_down_2", gP.tilesize, gP.tilesize);
        right1=setup("/Enemy/Walking_Sprites/greenslime_down_1", gP.tilesize, gP.tilesize);
        right2=setup("/Enemy/Walking_Sprites/greenslime_down_2", gP.tilesize, gP.tilesize);
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
}
