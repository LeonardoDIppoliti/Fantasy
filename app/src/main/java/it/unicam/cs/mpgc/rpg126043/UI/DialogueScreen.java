package it.unicam.cs.mpgc.rpg126043.UI;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.awt.*;

public class DialogueScreen {
    GamePanel gP;
    private String currentDialogue;

    public DialogueScreen(GamePanel gP) {
        this.gP = gP;

    }

    public void drawDialogueScreen(Graphics2D g2) {
        //WINDOW
     int   x = gP.tileSize * 2;
     int   y = gP.tileSize / 2;
     int   width = gP.screenWidth - (gP.tileSize * 4);
     int   height = gP.tileSize * 4;


       gP.ui.drawSubWindow(x, y, width, height);

        x += gP.tileSize;
        y += gP.tileSize;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 26F));

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += gP.tileSize;
        }


    }
    public void setCurrentDialogue(String currentDialogue) {
        this.currentDialogue = currentDialogue;
    }
}
