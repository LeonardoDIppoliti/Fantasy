package it.unicam.cs.mpgc.rpg126043.UI;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.awt.*;

public class ControlsScreen  extends SuperScreen {
    public ControlsScreen(GamePanel gP) {
      super(gP);

    }

    @Override
    public void drawScreen(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32f));

        //SUB WINDOW
        int frameX = gP.getTileSize() * 2;
        int frameY = gP.getTileSize();
        int width = gP.getTileSize() * 10;
        int height = gP.getTileSize() * 10;
        gP.ui.drawSubWindow(frameX, frameY, width, height);

        String text = "CONTROLS: ";
        int x = gP.ui.getXforCenteredText(text);
        int y = gP.getTileSize() * 3;
        g2.drawString(text, x, y);

        text = "W = UP \n A = LEFT \n S = DOWN \n D = RIGHT \n ESC = MENU/BACK \n ENTER = INTERACT";
        x = gP.ui.getXforCenteredText(text) + gP.getTileSize() * 8;
        y += gP.getTileSize();
        for (String line : text.split("\n")) {
            g2.drawString(line, x, y);
            y += gP.getTileSize();
        }

    }
}
