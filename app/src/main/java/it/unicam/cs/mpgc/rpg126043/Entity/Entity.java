package it.unicam.cs.mpgc.rpg126043.Entity;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;
import it.unicam.cs.mpgc.rpg126043.Main.UtilityTool;
import it.unicam.cs.mpgc.rpg126043.UI.UI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Entity {
    GamePanel gP;
    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public BufferedImage attackUp1,attackUp2,attackdown1,attackdown2,attackleft1,attackleft2,
            attackright1,attackright2;
    public Rectangle solidArea =  new Rectangle(0,0,48,48);
    public Rectangle attackArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX,solidAreaDefaultY;
    String[] dialogue = new String[20];
    public int type; //0 = player , 1 = NPC , 2 = Monster
    int frames = 40;


    //CHARACTER STATE
    public int worldX,worldY;
    public int spriteNum=1;
    public boolean invincible=false;
    public String directions ="down";
    public boolean collisionOn =false;
    int dialogueIndex=0;
    boolean attacking =false;
    boolean alive = true;
    boolean dying = false;
    boolean hpBarOn=false;

    //CHARACTERS ATTRIBUTES
    public int speed;
    public int maxLife;
    public int currentLife;
    public int defense;
    public int attack;
    protected int exp;
    public String name;

    //COUNTER
    public int spriteCount = 0;
    public int invincibleCounter =0;
    public int actionLockCounter=0;
    int dyingCounter=0;
    int hpBarCounter=0;



    public Entity(GamePanel gP){
        this.gP=gP;
    }

    public void setAction(){
    }

    public void damageReaction(){

    }
    public void update(){
        setAction();
        collisionOn=false;
        gP.cChecker.checkTile(this);
        gP.cChecker.checkEntity(this,gP.npc);
        gP.cChecker.checkEntity(this,gP.monster);
       boolean contactPlayer = gP.cChecker.checkPlayer(this);
       if(type==2 && contactPlayer && !gP.player.invincible){
           gP.combatHandler.contactMonster(this);
       }

        if (!collisionOn) {
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
        spriteCount++;
        if (spriteCount == 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCount = 0;
        }

        if(gP.ui.getGameStateIndex() == UI.GameState.playscreen.ordinal()) {
            if (invincible) {
                invincibleCounter++;
                if (invincibleCounter > frames) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
        }

    }


    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int screenX= worldX - gP.player.worldX + gP.player.screenX;
        int screenY= worldY - gP.player.worldY + gP.player.screenY;

        if(worldX + gP.tileSize >gP.player.worldX- gP.player.screenX &&
                worldX -gP.tileSize < gP.player.worldX+gP.player.screenX &&
                worldY +gP.tileSize >gP.player.worldY- gP.player.screenY &&
                worldY -gP.tileSize <gP.player.worldY + gP.player.screenY
        )
        {
            switch (directions) {
                case "up":
                        if (spriteNum == 1) {
                            image = up1;
                        }
                        if (spriteNum == 2) {
                            image = up2;
                        }

                    break;

                case "down":

                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":

                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left2;
                        }

                    break;
                case "right":
                       if (spriteNum == 1) {
                            image = right1;
                        }
                        if (spriteNum == 2) {
                            image = right2;
                        }

                    break;
            }
            //MONSTER HEALTH BAR
            if(type==2 && hpBarOn) {
                double oneScale=(double) gP.tileSize/maxLife;
                double hpBarValue=oneScale*currentLife;

                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX-1,screenY-16, gP.tileSize+2,12);

                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);

                hpBarCounter++;
                if(hpBarCounter>frames*15) {
                    hpBarCounter=0;
                    hpBarOn=false;
                }
            }
            if(invincible){
                hpBarOn=true;
                hpBarCounter=0;
                changeAlpha(g2,0.4F);
            }
            if(dying){
                dyingAnimation(g2);
            }
            g2.drawImage(image, screenX, screenY, gP.tileSize, gP.tileSize, null);

            changeAlpha(g2,1F);
        }
    }

    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        int i=5;
        if (dyingCounter <=5) {
            changeAlpha(g2,0f);
        }
        if (dyingCounter >i && dyingCounter<=i*2) {
            changeAlpha(g2,1f);}
        if (dyingCounter >i*2 && dyingCounter<=i*3) {
            changeAlpha(g2,0f);
        }
        if (dyingCounter >i*3 && dyingCounter<=i*4) {
            changeAlpha(g2,1f);
        }
        if (dyingCounter >i*4 && dyingCounter<=i*5) {
            changeAlpha(g2,0f);
        }
        if (dyingCounter >i*5 && dyingCounter<=i*6) {
            changeAlpha(g2,1f);
        }
        if (dyingCounter >i*6 && dyingCounter<=i*7) {
            changeAlpha(g2,0f);
        }
        if (dyingCounter >i*7 && dyingCounter<=i*8) {
            changeAlpha(g2,1f);
        }
        if (dyingCounter >i*8) {
           alive=false;
           dying=false;
        }
    }
    public void changeAlpha(Graphics2D g2,float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath,int width,int height){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath+".png")));
            image = uTool.scaleImage(image, width, height);

        }catch (Exception e){
            e.printStackTrace();
        }
        return image;
    }

    public void speak(){
        if(dialogue[dialogueIndex] == null)
        {
            dialogueIndex=0;
        }
        gP.getDialogueScreen().setCurrentDialogue(dialogue[dialogueIndex]);
        dialogueIndex++;

        switch(gP.player.directions){
            case "up":
                directions="down";
                break;

            case "down":
                directions="up";
                break;
            case "left":
                directions="right";
                break;
            case "right":
                directions="left";
                break;

        }
    }

    public boolean isAlive(){
        return alive;
    }
    public boolean isDead(){
        return dying;
    }
    public  void setDying(boolean dying){
        this.dying = dying;
    }
    public int getExp(){
        return exp;
    }
}

