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
        maxLife=10;
        currentLife=maxLife;
        defense = 6;
        attack = 12;
        exp=5;


        solidArea.x=3;
        solidArea.y=18;
        solidArea.width=42;
        solidArea.height=30;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;

        getImage();

    }

    public void getImage(){
        up1=setup("/Enemy/Images/greenslime_down_1", gP.tileSize, gP.tileSize);
        up2=setup("/Enemy/Images/greenslime_down_2", gP.tileSize, gP.tileSize);
        down1=setup("/Enemy/Images/greenslime_down_1", gP.tileSize, gP.tileSize);
        down2=setup("/Enemy/Images/greenslime_down_2", gP.tileSize, gP.tileSize);
        left1=setup("/Enemy/Images/greenslime_down_1", gP.tileSize, gP.tileSize);
        left2=setup("/Enemy/Images/greenslime_down_2", gP.tileSize, gP.tileSize);
        right1=setup("/Enemy/Images/greenslime_down_1", gP.tileSize, gP.tileSize);
        right2=setup("/Enemy/Images/greenslime_down_2", gP.tileSize, gP.tileSize);
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
            if(i > 75 ){
                directions="right";
            }
            actionLockCounter=0;
        }

    }
    @Override
    public void damageReaction(){
        actionLockCounter=0;
        directions = gP.player.directions;
    }
}
