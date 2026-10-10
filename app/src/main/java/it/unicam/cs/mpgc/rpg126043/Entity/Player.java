package it.unicam.cs.mpgc.rpg126043.Entity;

import it.unicam.cs.mpgc.rpg126043.Entity_Object.OBJ_Shield_Wood;
import it.unicam.cs.mpgc.rpg126043.Entity_Object.OBJ_Sword_Normal;
import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;
import it.unicam.cs.mpgc.rpg126043.Main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;


    public Player(GamePanel gP, KeyHandler keyH) {
        super(gP);
        this.keyH = keyH;
        screenX = gP.screenWidth/2 - (gP.tilesize/2);
        screenY = gP.screenHeight/2 - (gP.tilesize/2);

        solidArea =new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=32;
        solidArea.height=32;

        setDefaultValues();
        getPlayerImages();
    }
    public void getPlayerImages(){
        up1=setup("/PlayerImages/Walking_sprites/boy_up_1", gP.tilesize, gP.tilesize);
        up2=setup("/PlayerImages/Walking_sprites/boy_up_2", gP.tilesize, gP.tilesize);
        down1=setup("/PlayerImages/Walking_sprites/boy_down_1", gP.tilesize, gP.tilesize);
        down2=setup("/PlayerImages/Walking_sprites/boy_down_2", gP.tilesize, gP.tilesize);
        left1=setup("/PlayerImages/Walking_sprites/boy_left_1", gP.tilesize, gP.tilesize);
        left2=setup("/PlayerImages/Walking_sprites/boy_left_2", gP.tilesize, gP.tilesize);
        right1=setup("/PlayerImages/Walking_sprites/boy_right_1", gP.tilesize, gP.tilesize);
        right2=setup("/PlayerImages/Walking_sprites/boy_right_2", gP.tilesize, gP.tilesize);
    }


    public void setDefaultValues() {
        worldX= gP.tilesize*23;
        worldY= gP.tilesize*21;
        speed=4;
        directions="down";
    }
    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {
            if (keyH.upPressed) {
                directions = "up";
            } else if (keyH.downPressed) {
                directions = "down";
            } else if (keyH.leftPressed) {
                directions = "left";
            } else if (keyH.rightPressed) {
                directions = "right";
            }
            //CHECK TILE COLLISION
            collisionOn = false;
            gP.cChecker.checkTile(this);

            //CHECK OBJ COLLISION
           int objIndex = gP.cChecker.checkObject(this,true);
           pickUpObj(objIndex);
           //CHECK NPC COLLISION
           int npcIndex= gP.cChecker.checkEntity(this, gP.npc);
           interactNPC(npcIndex);
           //CHECK MONSTER COLLISION
           int monIndex= gP.cChecker.checkEntity(this, gP.monster);
           contactMonster(monIndex);
           //CHECK EVENT
           gP.eHhandler.checkEvent();

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn && !keyH.enterPressed) {
                switch (directions) {
                    case "up":
                        worldY -= speed;
                        break;

                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;

                }
            }

            gP.keyH.enterPressed = false;

            spriteCount++;
            if (spriteCount == 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCount = 0;
            }

        }

        if(gP.gameState == gP.playState) {
            if (invincible) {
                invincibleCounter++;
                if (invincibleCounter > 60) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
        }
    }
    public void pickUpObj(int i) {
        if(i !=999) {

        }
    }
    public void interactNPC(int i) {
        if (i !=999) {
            if(gP.keyH.enterPressed){
                gP.gameState = gP.dialogueState;
                gP.npc[i].speak();
            }

        }
    }
    public void contactMonster(int i){
        if (i != 999) {
            if (!invincible && gP.gameState == gP.playState) {
                      }
        }

    }


}
