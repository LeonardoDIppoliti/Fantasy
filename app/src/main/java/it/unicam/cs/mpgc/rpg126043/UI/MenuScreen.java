package it.unicam.cs.mpgc.rpg126043.UI;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.awt.*;

public class MenuScreen {
    GamePanel gP;

    public MenuScreen(GamePanel gP) {
        this.gP = gP;
    }

    public void drawMenuState(Graphics2D g2){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32f));

      int  frameX= gP.tileSize*6;
      int  frameY= gP.tileSize;
      int width = gP.tileSize*8;
      int height = gP.tileSize*10;

        //SUB WINDOW
        gP.ui.drawSubWindow(frameX,frameY,width,height);
        options(frameX,frameY,g2);
    }
    private void options(int frameX, int frameY, Graphics2D g2){
       int textX;
       int textY;

        //TITLE
        String text = "OPTIONS";
        textX = gP.ui.getXforCenteredText(text);
        textY = frameY + gP.tileSize;
        g2.drawString(text,textX,textY);

        //STATUS
        int cursorX=textX-15;
        textX=frameX+gP.tileSize;
        textY += gP.tileSize*2;
        g2.drawString("STATUS",textX,textY);
        if(gP.ui.getCommandNum()==gP.ui.getMinCommandNum()){
            g2.drawString(">",cursorX,textY);
        }
        //CONTROLS
        textY +=gP.tileSize;
        g2.drawString("CONTROLS",textX,textY);
        if(gP.ui.getCommandNum()==1){
            g2.drawString(">",cursorX,textY);
        }
        //END GAME
        textY +=gP.tileSize;
        g2.drawString("END GAME",textX,textY);
        if(gP.ui.getCommandNum()==2){
            g2.drawString(">",cursorX,textY);
        }
    }
}
