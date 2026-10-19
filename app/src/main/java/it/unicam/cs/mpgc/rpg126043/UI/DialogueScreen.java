package it.unicam.cs.mpgc.rpg126043.UI;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.awt.*;

public class DialogueScreen  extends SuperScreen {

    private String currentDialogue;

    public DialogueScreen(GamePanel gP) {
       super(gP);

    }

    @Override
    public void drawScreen(Graphics2D g2) {
        //WINDOW
     int   x = gP.getTileSize() * 2;
     int   y = gP.getTileSize() / 2;
     int   width = gP.screenWidth - (gP.getTileSize() * 4);
     int   height = gP.getTileSize() * 4;


       gP.ui.drawSubWindow(x, y, width, height);

        x += gP.getTileSize();
        y += gP.getTileSize();

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 26F));

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += gP.getTileSize();
        }


    }
    public void setCurrentDialogue(String currentDialogue) {
        this.currentDialogue = currentDialogue;
    }
}
