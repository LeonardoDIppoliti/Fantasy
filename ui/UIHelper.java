package it.unicam.cs.mpgc.rpg126043.ui;


import java.awt.*;

/** Shared drawing functions: windows, shaded text, alignments. */
public class UIHelper {

    private static final Font BASIC_FONT =new Font("Arial",Font.PLAIN,40);
    private UIHelper() {}

    public static void drawSubWindow(Graphics2D g2,int x, int y, int width, int height) {
        g2.setColor(UITheme.COLOR_WINDOW_BACKGROUND);
        g2.fillRoundRect(x, y, width, height, UITheme.WINDOW_ARC, UITheme.WINDOW_ARC);

        int inSet=UITheme.WINDOW_ARC_IN_SET;
        g2.setColor(UITheme.COLOR_WINDOW_BORDER);
        g2.setStroke(UITheme.WINDOW_STROKE);
        g2.drawRoundRect(x+inSet,y+inSet,width-2*inSet ,height-2*inSet,
                UITheme.WINDOW_ARC_BORDER, UITheme.WINDOW_ARC_BORDER);
    }

    public static void drawShadowed(Graphics2D g2,String text,int x, int y, int offSet) {
        g2.setColor(UITheme.COLOR_TEXT_SHADOW);
        g2.drawString(text,x+offSet,y+offSet);
        g2.setColor(UITheme.COLOR_TEXT);
        g2.drawString(text,x,y);
    }



    public static int getCenteredX(Graphics2D g2, int centerX, String text) {
        return centerX - (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth()/2;
    }

    public static int getAlignToRightX(Graphics2D g2, String text, int tailX) {
        return tailX - (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    }

}

