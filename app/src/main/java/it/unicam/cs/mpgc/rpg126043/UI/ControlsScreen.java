package it.unicam.cs.mpgc.rpg126043.UI;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.awt.*;

public class ControlsScreen {
    GamePanel gP;
    public ControlsScreen(GamePanel gP) {
        this.gP = gP;


    }


    public void drawControlsScreen(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32f));

        //SUB WINDOW
        int frameX = gP.tileSize * 2;
        int frameY = gP.tileSize;
        int width = gP.tileSize * 10;
        int height = gP.tileSize * 10;
        gP.ui.drawSubWindow(frameX, frameY, width, height);

        String text = "CONTROLS: ";
        int x = gP.ui.getXforCenteredText(text);
        int y = gP.tileSize * 3;
        g2.drawString(text, x, y);

        text = "W = UP \n A = LEFT \n S = DOWN \n D = RIGHT \n ESC = MENU/BACK \n ENTER = INTERACT";
        x = gP.ui.getXforCenteredText(text) + gP.tileSize * 8;
        y += gP.tileSize;
        for (String line : text.split("\n")) {
            g2.drawString(line, x, y);
            y += gP.tileSize;
        }

    }
}
