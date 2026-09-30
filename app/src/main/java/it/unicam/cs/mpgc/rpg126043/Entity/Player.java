package it.unicam.cs.mpgc.rpg126043.Entity;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;
import it.unicam.cs.mpgc.rpg126043.Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gP;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gP, KeyHandler keyH) {
        this.gP = gP;
        this.keyH = keyH;
        screenX= gP.screenWidth/2 - (gP.tilesize/2);
        screenY= gP.screenHeight/2 - (gP.tilesize/2);

        setDefaultValues();
        getPlayerImages();
    }
    public void getPlayerImages(){
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/PlayerImages/Walking_sprites/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/PlayerImages/Walking_sprites/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/PlayerImages/Walking_sprites/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/PlayerImages/Walking_sprites/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/PlayerImages/Walking_sprites/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/PlayerImages/Walking_sprites/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/PlayerImages/Walking_sprites/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/PlayerImages/Walking_sprites/boy_right_2.png")));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        worldX= gP.tilesize*23;
        worldY= gP.tilesize*21;
        speed=4;
        directions="left";
    }
    public void update(){
        if(keyH.upPressed == true){
            directions="up";
            worldY -= speed;
        }else if(keyH.downPressed == true){
            directions="down";
            worldY += speed;
        }else if(keyH.leftPressed == true){
            directions="left";
            worldX -= speed;
        }else if(keyH.rightPressed == true){
            directions="right";
            worldX += speed;
        }
        spriteCount++;
        if(spriteCount==10){
            if(spriteNum==1){
                spriteNum=2;
            }
            else if(spriteNum==2){
                spriteNum=1;
            }
            spriteCount=0;
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
        g2.drawImage(image, screenX, screenY, gP.tilesize,gP.tilesize, null);
    }
}
