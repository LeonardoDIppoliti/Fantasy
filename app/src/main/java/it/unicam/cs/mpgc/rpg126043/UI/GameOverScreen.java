package it.unicam.cs.mpgc.rpg126043.UI;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.awt.*;

public class GameOverScreen extends SuperScreen {

    public GameOverScreen(GamePanel gP) {
       super(gP);

    }
    @Override
    public void drawScreen(Graphics2D g2){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gP.screenWidth,gP.screenHeight);


       String text = "GAME OVER";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110F));
       int x=gP.ui.getXforCenteredText(text);
       int y=gP.getTileSize()*4;
        //SHADOW
        g2.setColor(Color.black);
        g2.drawString(text,x,y);
        //GAME OVER
        g2.setColor(Color.white);
        g2.drawString(text,x-4,y-4);


        g2.setFont(g2.getFont().deriveFont(50F));
        y+=gP.getTileSize()*3;
        if (!gP.gameFinished()) {
            //RETRY
            text = "RETRY";
            x = gP.ui.getXforCenteredText(text);
            g2.drawString(text, x, y);
            if (gP.ui.getCommandNum() == 0) {
                g2.drawString(">", x-gP.getTileSize(), y);

            }
            y+=60;
        }


        //BACK TO TITLE SCREEN IF GAME IS FINISHED CLOSES GAME
        text = "QUIT";
        x=gP.ui.getXforCenteredText(text);
        int index = gP.gameFinished()? 0:1;
        g2.drawString(text,x,y);
        if(gP.ui.getCommandNum()==index){
            g2.drawString(">",x-40,y);
        }

    }



}
