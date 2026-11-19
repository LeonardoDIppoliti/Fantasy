package it.unicam.cs.mpgc.rpg126043.ui.component;

import java.awt.*;
/** Reusable health bar (monsters and player). */
public final class HealthBar {
    private static final Color BACKGROUND = new Color(35,35,35);
    private static final Color FILL = new Color(255,0,30);
    private static final int BORDER = 1;

    private HealthBar(){   }

    public static void drawBar(Graphics2D g2, int x, int y, int width, int height, int currentLife, int maxLife){
        double ratio = maxLife > 0 ? Math.clamp((double) currentLife/maxLife,0.0,1.0) : 0;
        g2.setColor(BACKGROUND);
        g2.fillRect(x - BORDER,y - BORDER,width + 2 * BORDER, height + 2 * BORDER);
        g2.setColor(FILL);
        g2.fillRect(x,y,(int) (width*ratio), height);
    }


}
