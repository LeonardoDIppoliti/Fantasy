package it.unicam.cs.mpgc.rpg126043.UI;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.awt.*;

public class CharacterStatusScreen {
    GamePanel gP;

    public CharacterStatusScreen(GamePanel gP) {
        this.gP = gP;
    }

    public void drawCharacterStatsScreen(Graphics2D g2) {
        //CREATE FRAME

      int  frameX = gP.tileSize*2;
       int frameY = gP.tileSize;
      int  frameWidth = gP.tileSize*7;
       int frameHeight = gP.tileSize*10;
        gP.ui.drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //WRITE TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(22f));



        //NAMES

        int  textX=frameX + 20;
        int   textY=frameY + gP.tileSize;
        int lineHeight=35;
        getStatsNames(g2,textX,textY,lineHeight);

        //VALUES
        int tailX= (frameX+frameWidth)-30;
        textY=frameY + gP.tileSize;
        String value;

        value = gP.player.getName();
        textX=gP.ui.getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = String.valueOf(gP.player.getLevel());
        textX=gP.ui.getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;


        value = gP.player.getCurrentLife() + "/" + gP.player.getMaxLife();
        textX=gP.ui.getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = String.valueOf(gP.player.getStrength());
        textX=gP.ui.getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = String.valueOf(gP.player.getDefense());
        textX=gP.ui.getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = String.valueOf(gP.player.getExp());
        textX=gP.ui.getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = String.valueOf(gP.player.getNextLevelExp());
        textX=gP.ui.getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);
        textY+=lineHeight;

        value = "Sword";
        textX=gP.ui.getXforAlignToRightText(value,tailX);
        g2.drawString(value,textX,textY);



    }
    private void getStatsNames(Graphics2D g2,int textX,int textY,int lineHeight){
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
        g2.drawString("EXP",textX,textY);
        textY+=lineHeight;
        g2.drawString("EXP Next Level",textX,textY);
        textY+=lineHeight;
        g2.drawString("Weapon",textX,textY);
    }
}


