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
    public String currentDialogue;
    public int commandNum =0;
    int titleScreenState=0; // 0: FIRST PAGE 1: SECOND PAGE

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

        //TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        //PLAY STATE
        if(gp.gameState == gp.playState) {
            //DO LATER
        }
        //PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        //DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }

    }
    public void drawTitleScreen(){
        if(titleScreenState==0){

            //BACKGROUND COLOR
            g2.setColor(new Color(60,70,70));
            g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);


            //TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
            String text = "FANTASY";
            int x = getXforCenteredText(text);
            int y = gp.tilesize*3;

            //SHADOW
            g2.setColor(Color.black);
            g2.drawString(text,x+5,y+5);
            //MAIN TEXT
            g2.setColor(Color.white);
            g2.drawString(text,x,y);

            //CHARACTER IMAGE
            x = gp.screenWidth/2-(gp.tilesize*2)/2;
            y += gp.tilesize*2;
            g2.drawImage(gp.player.down1,x,y,gp.tilesize*2,gp.tilesize*2,null);

            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));

            text = "START GAME";
            x = getXforCenteredText(text);
            y += gp.tilesize * 3.5;
            g2.drawString(text,x,y);
            if(commandNum==0){
                g2.drawString("=>",x-gp.tilesize-8,y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tilesize;
            g2.drawString(text,x,y);
            if(commandNum==1){
                g2.drawString("=>",x-gp.tilesize-8,y);
            }

            text = "QUIT";
            x = getXforCenteredText(text);
            y += gp.tilesize;
            g2.drawString(text,x,y);
            if(commandNum==2){
                g2.drawString("=>",x-gp.tilesize-8,y);
            }


        } else if(titleScreenState==1){

            //BACKGROUND COLOR
            g2.setColor(new Color(60,70,70));
            g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

            //CONTROLS SCREEN
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));

            String text = "CONTROLS: ";
            int x = getXforCenteredText(text);
            int y = gp.tilesize*3;
            g2.drawString(text,x,y);

            text = "W = UP \n A = LEFT \n S = DOWN \n D = RIGHT \n ENTER = INTERACT";
            x = getXforCenteredText(text) + gp.tilesize*8;
            y += gp.tilesize;
            for(String line : text.split("\n")){
                g2.drawString(line,x,y);
                y +=gp.tilesize;
            }

            text = "CONTINUE";
            x = getXforCenteredText(text);
            y = gp.tilesize*10;
            g2.drawString(text,x,y);
            if(commandNum==0){
                g2.drawString("=>",x-gp.tilesize,y);
            }

        }
    }
    public void drawDialogueScreen(){
        //WINDOW
        int x = gp.tilesize*2;
        int y = gp.tilesize/2;
        int width=gp.screenWidth - (gp.tilesize*4) ;
        int height=gp.tilesize*4;

        drawSubWindow(x,y,width,height);

        x += gp.tilesize;
        y += gp.tilesize;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 26F));

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y+=gp.tilesize;
        }


    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
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
