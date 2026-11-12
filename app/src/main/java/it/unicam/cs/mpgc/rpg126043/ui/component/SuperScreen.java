package it.unicam.cs.mpgc.rpg126043.ui.component;

import it.unicam.cs.mpgc.rpg126043.interfaces.InputState;
import it.unicam.cs.mpgc.rpg126043.interfaces.UIContext;
import it.unicam.cs.mpgc.rpg126043.ui.ScreenNavigator;

import java.awt.*;

/** Basic screen: draws itself and handles its own input. */
public abstract class SuperScreen {
   protected final UIContext uiContext;
   protected final ScreenNavigator navi;
    public SuperScreen(UIContext uiContext, ScreenNavigator nav) {
        this.uiContext = uiContext;
        this.navi = nav;
    }

    public void onEnter(){}
    public void update(InputState input) {}
    public abstract void draw(Graphics2D g2);
}
