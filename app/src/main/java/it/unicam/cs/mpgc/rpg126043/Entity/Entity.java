package it.unicam.cs.mpgc.rpg126043.Entity;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;
import it.unicam.cs.mpgc.rpg126043.Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Entity {
    public BufferedImage image,image2,image3;
    public boolean collision=false;
    GamePanel gP;
    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public Rectangle solidArea =  new Rectangle(0,0,48,48);
    public int solidAreaDefaultX,solidAreaDefaultY;
    String[] dialogue = new String[20];
    public int type; //0 = player , 1 = NPC , 2 = Monster

    //CHARACTER STATE
    public int worldX,worldY;
    public int spriteNum=1;
    public boolean invincible=false;
    public String directions ="down";
    public boolean collisionOn =false;
    int dialogueIndex=0;

    //CHARACTERS ATTRIBUTES
    public int speed;
    public int maxLife;
    public int currentLife;
    public int defense;
    public int attack;
    public String name;



    //COUNTER
    public int spriteCount = 0;
    public int invincibleCounter =0;
    public int actionLockCounter=0;



    public Entity(GamePanel gP){
        this.gP=gP;
    }
    public void setAction(){}
    public void update(){
        setAction();
        collisionOn=false;
        gP.cChecker.checkTile(this);
        gP.cChecker.checkObject(this,false);
        gP.cChecker.checkEntity(this,gP.npc);
        gP.cChecker.checkEntity(this,gP.monster);
       boolean contactPlayer = gP.cChecker.checkPlayer(this);

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

    }


    public void draw(Graphics2D g2){

        BufferedImage image =null;
        int screenX= worldX - gP.player.worldX + gP.player.screenX;
        int screenY= worldY - gP.player.worldY + gP.player.screenY;

        if(worldX + gP.tilesize >gP.player.worldX- gP.player.screenX &&
                worldX -gP.tilesize < gP.player.worldX+gP.player.screenX &&
                worldY +gP.tilesize >gP.player.worldY- gP.player.screenY &&
                worldY -gP.tilesize <gP.player.worldY + gP.player.screenY
        )
        {
            switch (directions){
                case "up":
                    if(spriteNum==1){
                        image = up1;
                    }
                    if(spriteNum==2){
                        image = up2;
                    }
                    break;

                case "down":
                    if(spriteNum==1){
                        image = down1;
                    }
                    if(spriteNum==2){
                        image = down2;
                    }
                    break;
                case "left":
                    if(spriteNum==1){
                        image = left1;
                    }
                    if(spriteNum==2){
                        image = left2;
                    }
                    break;
                case "right":
                    if(spriteNum==1){
                        image = right1;
                    }
                    if(spriteNum==2){
                        image = right2;
                    }
                    break;
            }
            g2.drawImage(image,screenX,screenY,gP.tilesize, gP.tilesize, null);
        }
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
        gP.ui.currentDialogue = dialogue[dialogueIndex];
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




}
