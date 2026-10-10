package it.unicam.cs.mpgc.rpg126043.Main;

import it.unicam.cs.mpgc.rpg126043.Entity.Entity;
import it.unicam.cs.mpgc.rpg126043.Entity.Party_Member;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue;
    public int commandNum = 0;
    int titleScreenState = 0; // 0: FIRST PAGE 1: SECOND PAGE
    int subState=0;

    int memberNum=0;


    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("arial", Font.PLAIN, 40);
        arial_80 = new Font("arial", Font.BOLD, 80);

    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        //TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        //PLAY STATE
        if (gp.gameState == gp.playState) {
            drawPartyLife();
        }
        //PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPartyLife();
            drawPauseScreen();
        }
        //DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawPartyLife();
            drawDialogueScreen();
        }
        //CHARACTER STATE
        if(gp.gameState == gp.characterState){
            drawCharacterStatsScreen();
        }
        //OPTIONS STATE
        if(gp.gameState == gp.optionState){
            drawOptionState();
        }

    }
    public void drawOptionState(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32f));

        //SUB WINDOW
        int frameX= gp.tilesize*6;
        int frameY= gp.tilesize;
        int width = gp.tilesize*8;
        int height = gp.tilesize*10;
        drawSubWindow(frameX,frameY,width,height);

        switch(subState){
            case 0: optionTop(frameX,frameY); break;
            case 1: break;
            case 2: break;
        }
    }
public void optionTop(int frameX, int frameY){
    int textX;
    int textY;

    //TITLE
    String text = "OPTIONS";
    textX=getXforCenteredText(text);
    textY = frameY + gp.tilesize;
    g2.drawString(text,textX,textY);
}



    public void drawPartyLife(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));

        final int startX= gp.tilesize/2;
        final int startY= gp.tilesize;
        final int rowHeight = 30;
        final int gap = 15;

        //Find Biggest width for character name
        int maxWidth=0;
        for(Party_Member m : gp.pHandler.getParty()) {
            int width = (int) g2.getFontMetrics().getStringBounds(m.getName(),g2).getWidth();
            if(width>maxWidth){
                maxWidth=width;
            }
        }

        int valueX = startX + maxWidth+ gap;
        int row=0;
        for(Party_Member m : gp.pHandler.getParty()){
            int y = startY+row+rowHeight ;
            String name = m.getName();
            String life = m.getCurrentLife() + "/" + m.getMaxLife();

            //COLOR BASED ON LIFE AMOUNT
            Color c;
            if (m.getCurrentLife() == 0) {
                g2.setColor(c = new Color(0,0,0));}else
            if(m.getCurrentLife()<=m.getMaxLife()/4){
                g2.setColor(c = new Color(255,60,60));
            } else{c=Color.WHITE;}


            //SHADOW
            g2.setColor(Color.black);
            g2.drawString(name,startX+3,y+3);
            g2.drawString(life,valueX+3,y+3);

            //NAME + LIFE
            g2.setColor(c);
            g2.drawString(name,startX,y);
            g2.drawString(life,valueX,y);

            row+=gp.tilesize;
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
            y += (int) (gp.tilesize * 3.5);
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

            text = "W = UP \n A = LEFT \n S = DOWN \n D = RIGHT \n P = PAUSE \n ENTER = INTERACT";
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
    public int getXforAlignToRightText(String text,int tailX){
        int lenght = (int) g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = tailX-lenght;
        return x;
    }

    public void drawCharacterStatsScreen() {
        //CREATE FRAME
        final int frameX = gp.tilesize*2;
        final int frameY = gp.tilesize;
        final int frameWidth = gp.tilesize*7;
        final int frameHeight = gp.tilesize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //WRITE TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(22f));
        int textX=frameX + 20;
        int textY=frameY + gp.tilesize;
        final int lineHeight=35;

        //NAMES
        g2.drawString("Name",textX,textY);
        textY+=lineHeight;
        g2.drawString("Level",textX,textY);
        textY+=lineHeight;
        g2.drawString("Life",textX,textY);
        textY+=lineHeight;
        g2.drawString("Strength",textX,textY);
        textY+=lineHeight;
        g2.drawString("Dexterity",textX,textY);
        textY+=lineHeight;
        g2.drawString("Attack",textX,textY);
        textY+=lineHeight;
        g2.drawString("Defense",textX,textY);
        textY+=lineHeight;
        g2.drawString("Exp",textX,textY);
        textY+=lineHeight;
        g2.drawString("Next Level",textX,textY);
        textY+=lineHeight;
        g2.drawString("Weapon",textX,textY);
        textY+=lineHeight;
        g2.drawString("Shield",textX,textY);

        //VALUES
        int tailX= (frameX+frameWidth)-30;
        textY=frameY + gp.tilesize;
        String value;
        Party_Member pMember = gp.pHandler.getParty().get(memberNum);

        value = pMember.getName() + "  " + (memberNum+1) +"/" +gp.pHandler.getPartySize();
        textX=getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = String.valueOf(pMember.getLevel());
        textX=getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = pMember.getCurrentLife() + "/" + pMember.getMaxLife();
        textX=getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = String.valueOf(pMember.getStrength());
        textX=getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = String.valueOf(pMember.getDexterity());
        textX=getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = String.valueOf(pMember.getAttack());
        textX=getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = String.valueOf(pMember.getDefense());
        textX=getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = String.valueOf(pMember.getExp());
        textX=getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = String.valueOf(pMember.getNextLevelExp());
        textX=getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value=pMember.getCurrentWeapon().name;
        textX=getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value=pMember.getCurrentShield().name;
        textX=getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
    }
}
