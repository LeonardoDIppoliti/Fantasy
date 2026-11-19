package it.unicam.cs.mpgc.rpg126043.main;

import it.unicam.cs.mpgc.rpg126043.entity.Player;
import it.unicam.cs.mpgc.rpg126043.interfaces.Camera;

import java.awt.*;
/** Player-centered camera: converts world coordinates to screen coordinates. */
public class PlayerCamera implements Camera {
    private final Player player;
    private final int screenWidth,screenHeight;

    public PlayerCamera(Player player, int screenWidth, int screenHeight) {
        this.player = player;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public int toScreenX(int worldX) {
       return worldX-player.getWorldX()+player.getScreenX();
    }

    @Override
    public int toScreenY(int worldY) {
        return worldY-player.getWorldY()+player.getScreenY();
    }

    @Override
    public Rectangle getViewBounds() {
        return new Rectangle(player.getWorldX()-player.getScreenX(),
                player.getWorldY()-player.getScreenY(),
                screenWidth,screenHeight);
    }
}
