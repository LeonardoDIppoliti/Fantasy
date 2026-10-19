package it.unicam.cs.mpgc.rpg126043.UI;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.awt.*;

public class MenuScreen  extends SuperScreen{


    public MenuScreen(GamePanel gP) {
        super(gP);
    }
    @Override
    public void drawScreen(Graphics2D g2){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32f));

      int  frameX= gP.getTileSize()*6;
      int  frameY= gP.getTileSize();
      int width = gP.getTileSize()*8;
      int height = gP.getTileSize()*10;

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
        textY = frameY + gP.getTileSize();
        g2.drawString(text,textX,textY);

        //STATUS
        int cursorX=textX-15;
        textX=frameX+gP.getTileSize();
        textY += gP.getTileSize()*2;
        g2.drawString("STATUS",textX,textY);
        if(gP.ui.getCommandNum()==gP.ui.getMinCommandNum()){
            g2.drawString(">",cursorX,textY);
        }
        //CONTROLS
        textY +=gP.getTileSize();
        g2.drawString("CONTROLS",textX,textY);
        if(gP.ui.getCommandNum()==1){
            g2.drawString(">",cursorX,textY);
        }
        //END GAME
        textY +=gP.getTileSize();
        g2.drawString("END GAME",textX,textY);
        if(gP.ui.getCommandNum()==2){
            g2.drawString(">",cursorX,textY);
        }
    }
}
