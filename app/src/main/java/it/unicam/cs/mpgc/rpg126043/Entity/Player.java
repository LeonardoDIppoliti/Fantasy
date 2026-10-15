package it.unicam.cs.mpgc.rpg126043.Entity;


import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;
import it.unicam.cs.mpgc.rpg126043.Main.KeyHandler;
import it.unicam.cs.mpgc.rpg126043.UI.UI;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    private int level;
    private int exp;
    private int nextLevelExp;
    private boolean attackCancelled=false;



    public Player(GamePanel gP, KeyHandler keyH) {
        super(gP);
        this.keyH = keyH;
        screenX = gP.screenWidth/2 - (gP.tileSize/2);
        screenY = gP.screenHeight/2 - (gP.tileSize/2);

        solidArea =new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=32;
        solidArea.height=32;

        attackArea.width=36;
        attackArea.height=36;

        setDefaultValues();
        getPlayerImages();
        getPlayerAttackImages();
    }
    public void setDefaultPosition(){
        worldX= gP.tileSize*23;
        worldY= gP.tileSize*21;

        directions="down";
    }
    public void restoreStatus(){
        currentLife=maxLife;
        invincible=false;
        attacking=false;
    }
    public void getPlayerImages(){
        up1=setup("/PlayerImages/Walking_sprites/boy_up_1", gP.tileSize, gP.tileSize);
        up2=setup("/PlayerImages/Walking_sprites/boy_up_2", gP.tileSize, gP.tileSize);
        down1=setup("/PlayerImages/Walking_sprites/boy_down_1", gP.tileSize, gP.tileSize);
        down2=setup("/PlayerImages/Walking_sprites/boy_down_2", gP.tileSize, gP.tileSize);
        left1=setup("/PlayerImages/Walking_sprites/boy_left_1", gP.tileSize, gP.tileSize);
        left2=setup("/PlayerImages/Walking_sprites/boy_left_2", gP.tileSize, gP.tileSize);
        right1=setup("/PlayerImages/Walking_sprites/boy_right_1", gP.tileSize, gP.tileSize);
        right2=setup("/PlayerImages/Walking_sprites/boy_right_2", gP.tileSize, gP.tileSize);
    }
    public void getPlayerAttackImages(){
        attackUp1=setup("/PlayerImages/Attacking_sprites/boy_attack_up_1", gP.tileSize, gP.tileSize*2);
        attackUp2=setup("/PlayerImages/Attacking_sprites/boy_attack_up_2", gP.tileSize, gP.tileSize*2);
        attackdown1=setup("/PlayerImages/Attacking_sprites/boy_attack_down_1", gP.tileSize, gP.tileSize*2);
        attackdown2=setup("/PlayerImages/Attacking_sprites/boy_attack_down_2", gP.tileSize, gP.tileSize*2);
        attackleft1 =setup("/PlayerImages/Attacking_sprites/boy_attack_left_1", gP.tileSize*2, gP.tileSize);
        attackleft2=setup("/PlayerImages/Attacking_sprites/boy_attack_left_2", gP.tileSize*2, gP.tileSize);
        attackright1=setup("/PlayerImages/Attacking_sprites/boy_attack_right_1", gP.tileSize*2, gP.tileSize);
        attackright2=setup("/PlayerImages/Attacking_sprites/boy_attack_right_2", gP.tileSize*2, gP.tileSize);
    }


    public void setDefaultValues() {
        worldX= gP.tileSize*23;
        worldY= gP.tileSize*21;

        directions="down";
        speed=6;

        //STATUS
        name="Player";
        level=1;
        attack=10;
        defense=10;
        maxLife=12;
        currentLife=maxLife;
        exp=1;
        nextLevelExp=5;
        attacking=false;

    }
    public void update() {
        if(attacking){
            attacks();
        }

        else if (keyH.isUpPressed() || keyH.isDownPressed() || keyH.isLeftPressed() || keyH.isRightPressed() || keyH.isEnterPressed()) {
            if (keyH.isUpPressed()) {
                directions = "up";
            } else if (keyH.isDownPressed()) {
                directions = "down";
            } else if (keyH.isLeftPressed()) {
                directions = "left";
            } else if (keyH.isRightPressed()) {
                directions = "right";
            }
            //CHECK TILE COLLISION
            collisionOn = false;
            gP.cChecker.checkTile(this);

           //CHECK NPC COLLISION
           int npcIndex= gP.cChecker.checkEntity(this, gP.npc);
           interactNPC(npcIndex);
           //CHECK MONSTER COLLISION
           int monIndex= gP.cChecker.checkEntity(this, gP.monster);
           if(monIndex != 999){
              gP.combatHandler.contactMonster(gP.monster[monIndex]);
           }
           //CHECK EVENT
           gP.eHhandler.checkEvent();

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn && !keyH.isEnterPressed()) {
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

            if(!attackCancelled && keyH.isEnterPressed()) {
                attacking=true;
                spriteCount=0;
            }
            attackCancelled=false;

            gP.keyH.setEnterPressed(false);

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

        if(gP.ui.getGameStateIndex() == UI.GameState.playscreen.ordinal()) {
            if (invincible) {
                invincibleCounter++;
                int frames = 60;
                if (invincibleCounter > frames) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
        }
        if(currentLife <= 0) {
            gP.ui.showGameOver(false);
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image=null;
        int tempScreenX=screenX;
        int tempScreenY=screenY;

        switch (directions) {
            case "up":
                if(!attacking){
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                if (attacking){
                    tempScreenY=screenY- gP.tileSize;
                    if (spriteNum == 1) {
                        image = attackUp1;
                    }
                    if (spriteNum == 2) {
                        image = attackUp2;
                    }
                }
                break;

            case "down":
                if(!attacking){

                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }

                if (attacking){
                    if (spriteNum == 1) {
                        image = attackdown1;
                    }
                    if (spriteNum == 2) {
                        image = attackdown2;
                    }
                }
                break;
            case "left":
                if(!attacking){

                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }

                if (attacking){
                    tempScreenX=screenX- gP.tileSize;
                    if (spriteNum == 1) {
                        image = attackleft1;
                    }
                    if (spriteNum == 2) {
                        image = attackleft2;
                    }
                }
                break;
            case "right":
                if(!attacking){

                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }

                if (attacking){
                    if (spriteNum == 1) {
                        image = attackright1;
                    }
                    if (spriteNum == 2) {
                        image = attackright2;
                    }
                }
                break;
        }
        if(invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        //reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    public void interactNPC(int i) {
        if(gP.keyH.isEnterPressed()) {
            if (i !=999) {
                attackCancelled=true;
                gP.ui.setGameStateIndex(UI.GameState.dialogueScreen.ordinal());
                gP.npc[i].speak();

            }
         }
    }

    public void attacks(){
        spriteCount++;
        if (spriteCount <= 5) {
            spriteNum = 1;
        }
        if (spriteCount >5 && spriteCount <= 25) {
            spriteNum = 2;
            //save the current worldX,worldY and solidArea
            int currentWorldX=worldX;
            int currentWorldY=worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //adjust player worldX,worldY for attackArea
            switch (directions) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY +=attackArea.height; break;
                case "left": worldX -=attackArea.width; break;
                case "right":worldX+=attackArea.width; break;
            }
            //attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            //check monster collision with updated worldX,worldY,solidArea
            int monsterIndex = gP.cChecker.checkEntity(this, gP.monster);
            gP.combatHandler.damageMonster(monsterIndex);
            //after checking collision restore original data
            worldX=currentWorldX;
            worldY=currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCount > 25){
            spriteNum = 1;
            spriteCount = 0;
            attacking = false;
        }
    }

    public void checkLevelUp(){
        if(exp>=nextLevelExp){
            level++;
            nextLevelExp=nextLevelExp*4;
            maxLife+=2;
            attack++;
            defense++;

            gP.getDialogueScreen().setCurrentDialogue("You are level " + level + " now! \n" + "You feel Stronger!");
            gP.ui.setGameStateIndex(UI.GameState.dialogueScreen.ordinal());

        }
    }

    public String getName(){
        return name;
    }
    public int getCurrentLife() {
        return currentLife;
    }
    public int getMaxLife() {
        return maxLife;
    }
    public int getStrength() {
        return attack;
    }
    public int getDefense() {
        return defense;
    }
    public int getLevel(){
        return level;
    }
    public int getExp(){
        return exp;
    }
    public int getNextLevelExp(){
        return nextLevelExp;
    }
    public boolean getInvincible(){
        return invincible;
    }
    public void setCurrentLife(int life) {
        this.currentLife = life;
    }
    public void setMaxLife(int life) {
        this.maxLife = life;
    }
    public void setStrength(int strength) {
        this.attack = strength;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setExp(int exp) {
        this.exp = exp;
    }
    public void setNextLevelExp(int nextLevelExp) {
        this.nextLevelExp = nextLevelExp;
    }
    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }
    public void isAttackCancelled(boolean attack){
        this.attackCancelled=attack;
    }


}

