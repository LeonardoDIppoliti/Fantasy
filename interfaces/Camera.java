package it.unicam.cs.mpgc.rpg126043.interfaces;

import java.awt.*;

public interface Camera {
    int toScreenX(int worldX);
    int toScreenY(int worldY);
    Rectangle getViewBounds();

}
