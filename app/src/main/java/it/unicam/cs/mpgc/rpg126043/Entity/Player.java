package it.unicam.cs.mpgc.rpg126043.Entity;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;
import it.unicam.cs.mpgc.rpg126043.Main.KeyHandler;
import it.unicam.cs.mpgc.rpg126043.Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gP;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gP, KeyHandler keyH) {
        this.gP = gP;
        this.keyH = keyH;
        screenX= gP.screenWidth/2 - (gP.tilesize/2);
        screenY= gP.screenHeight/2 - (gP.tilesize/2);

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
        up1=setup("boy_up_1");
        up2=setup("boy_up_2");
        down1=setup("boy_down_1");
        down2=setup("boy_down_2");
        left1=setup("boy_left_1");
        left2=setup("boy_left_2");
        right1=setup("boy_right_1");
        right2=setup("boy_right_2");
    }

    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/PlayerImages/Walking_sprites/"+imageName+".png")));
            image = uTool.scaleImage(image, gP.tilesize, gP.tilesize);

        }catch (Exception e){
            e.printStackTrace();
        }
        return image;
    }

    public void setDefaultValues() {
        worldX= gP.tilesize*23;
        worldY= gP.tilesize*21;
        speed=4;
        directions="down";
    }
    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed == true) {
                directions = "up";
            } else if (keyH.downPressed == true) {
                directions = "down";
            } else if (keyH.leftPressed == true) {
                directions = "left";
            } else if (keyH.rightPressed == true) {
                directions = "right";
            }
            //CHECK TILE COLLISION
            collisionOn = false;
            gP.cChecker.checkTile(this);

            //CHECK OBJ COLLISION
           int objIndex = gP.cChecker.checkObject(this,true);
           pickUpObj(objIndex);

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
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
    }
    public void pickUpObj(int i) {
        if (i !=999) {
            String objName = gP.obj[i].name;
            switch (objName) {
                case "Key":
                    hasKey++;
                    gP.obj[i] =null;
                    gP.ui.showMessage("You got a Key! ");
                    break;
                case "Door":
                    if(hasKey > 0){
                        gP.obj[i] =null;
                        hasKey--;
                        gP.ui.showMessage("You opened the door ");
                    }else{
                        gP.ui.showMessage("You need a key! ");
                    }
                    break;
                case "Boots":
                    speed +=2;
                    gP.obj[i] =null;
                    gP.ui.showMessage("Speed up! ");
                    break;
                case "Chest":
                    gP.ui.gameFinished=true;
                    break;
            }
        }

    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
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
        g2.drawImage(image, screenX, screenY, null);
    }
}
