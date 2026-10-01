package it.unicam.cs.mpgc.rpg126043.Main;

import it.unicam.cs.mpgc.rpg126043.Object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40,arial_80;
    public boolean messageOn=false;
    public String message="";
    int messageCounter=0;
    public boolean gameFinished =false;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("arial", Font.PLAIN, 40);
        arial_80 = new Font("arial", Font.BOLD, 80);
    }
    public void showMessage(String text){
        message=text;
        messageOn=true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if(gp.gameState == gp.playState) {

        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }

    }

    public void drawPauseScreen(){
        String text ="PAUSED";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        int x=getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text,x,y);
    }
    public int getXforCenteredText(String text){
        int lenght = (int) g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - lenght/2;
        return x;
    }
}
