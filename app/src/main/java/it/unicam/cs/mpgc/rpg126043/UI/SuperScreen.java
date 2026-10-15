package it.unicam.cs.mpgc.rpg126043.UI;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.awt.*;

/**
 * SuperClasse degli stati a schermo
 */
public abstract class SuperScreen {
    GamePanel gP;
    Graphics2D g2;
    public SuperScreen(GamePanel gP) {
        this.gP = gP;
    }

    public void draw(Graphics2D g2){

    }
}
