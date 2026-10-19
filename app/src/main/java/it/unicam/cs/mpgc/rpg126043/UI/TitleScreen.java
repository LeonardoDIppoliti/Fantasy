package it.unicam.cs.mpgc.rpg126043.UI;

import it.unicam.cs.mpgc.rpg126043.Entity.Direction;
import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.awt.*;

public class TitleScreen  extends SuperScreen{

    public TitleScreen(GamePanel gP) {
       super(gP);
    }

    @Override
    public void drawScreen(Graphics2D g2) {
        int x, y;
        //BACKGROUND COLOR
        g2.setColor(new Color(60, 70, 70));
        g2.fillRect(0, 0, gP.screenWidth, gP.screenHeight);


        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "FANTASY";
        x=gP.ui.getXforCenteredText(text);
        y = gP.getTileSize() * 3;
        //SHADOW
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);
        //MAIN TEXT
        x = gP.ui.getXforCenteredText(text);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //CHARACTER IMAGE
        x = gP.screenWidth / 2 - (gP.getTileSize() * 2) / 2;
        y += gP.getTileSize() * 2;
        g2.drawImage(gP.player.getSprites().get(Direction.DOWN,1), x, y, gP.getTileSize() * 2, gP.getTileSize() * 2, null);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

        text = "START GAME";
        x = gP.ui.getXforCenteredText(text);
        y += (int) (gP.getTileSize() * 3.5);
        g2.drawString(text, x, y);
        if (gP.ui.getCommandNum() == 0) {
            x -= gP.getTileSize();
            g2.drawString("=>", x, y);
        }
        text = "LOAD GAME";
        x = gP.ui.getXforCenteredText(text);
        y += gP.getTileSize();
        g2.drawString(text, x, y);
        if (gP.ui.getCommandNum() == 1) {
            x -= gP.getTileSize();
            g2.drawString("=>", x, y);
        }
            text = "QUIT";
            x = gP.ui.getXforCenteredText(text);
            y += gP.getTileSize();
            g2.drawString(text, x, y);
            if (gP.ui.getCommandNum() == 2) {
                x -= gP.getTileSize();
                g2.drawString("=>", x, y);


            }


    }
}