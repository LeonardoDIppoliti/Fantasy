package it.unicam.cs.mpgc.rpg126043.UI;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.awt.*;

public class TitleScreen {
    GamePanel gP;

    public TitleScreen(GamePanel gP) {
        this.gP=gP;
    }

    public void drawTitleScreen(Graphics2D g2) {
        int x, y;
        //BACKGROUND COLOR
        g2.setColor(new Color(60, 70, 70));
        g2.fillRect(0, 0, gP.screenWidth, gP.screenHeight);


        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "FANTASY";
        x=gP.ui.getXforCenteredText(text);
        y = gP.tileSize * 3;
        //SHADOW
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);
        //MAIN TEXT
        x = gP.ui.getXforCenteredText(text);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //CHARACTER IMAGE
        x = gP.screenWidth / 2 - (gP.tileSize * 2) / 2;
        y += gP.tileSize * 2;
        g2.drawImage(gP.player.down1, x, y, gP.tileSize * 2, gP.tileSize * 2, null);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

        text = "START GAME";
        x = gP.ui.getXforCenteredText(text);
        y += (int) (gP.tileSize * 3.5);
        g2.drawString(text, x, y);
        if (gP.ui.getCommandNum() == 0) {
            x -= gP.tileSize;
            g2.drawString("=>", x, y);
        }
        text = "LOAD GAME";
        x = gP.ui.getXforCenteredText(text);
        y += gP.tileSize;
        g2.drawString(text, x, y);
        if (gP.ui.getCommandNum() == 1) {
            x -= gP.tileSize;
            g2.drawString("=>", x, y);
        }
            text = "QUIT";
            x = gP.ui.getXforCenteredText(text);
            y += gP.tileSize;
            g2.drawString(text, x, y);
            if (gP.ui.getCommandNum() == 2) {
                x -= gP.tileSize;
                g2.drawString("=>", x, y);


            }


    }
}