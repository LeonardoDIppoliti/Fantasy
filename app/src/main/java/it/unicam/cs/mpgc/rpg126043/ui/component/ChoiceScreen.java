package it.unicam.cs.mpgc.rpg126043.ui.component;

import it.unicam.cs.mpgc.rpg126043.interfaces.InputState;
import it.unicam.cs.mpgc.rpg126043.interfaces.UIContext;
import it.unicam.cs.mpgc.rpg126043.ui.ScreenNavigator;
import it.unicam.cs.mpgc.rpg126043.ui.UIHelper;

import java.awt.*;
import java.util.List;
/** Screen with selectable items (Template Method): cursor, ENTER, and ESC are handled here. */
public abstract class ChoiceScreen extends SuperScreen {

    private static final int CURSOR_OFF_SET =40;
    private final MenuCursor cursor = new MenuCursor();
    private List<MenuEntry> entries = List.of();


    public ChoiceScreen(UIContext uiContext, ScreenNavigator nav) {

        super(uiContext, nav);
    }
    /*
    * Template Method for all line-based screens: handles cursor, ENTER, and ESC.
    * Subclasses only declare the lines and how to draw them.
    * */
    protected void setMenuEntries(List<MenuEntry> entries){
        this.entries = entries;
        cursor.resetIndex();
    }


    protected void onBack(){}

    @Override
    public void onEnter(){
        cursor.resetIndex();
    }

    @Override
    public void update(InputState input) {
        cursor.update(input, entries.size());
        if (input.consumeEsc()) {
            onBack();
        }
        if (input.consumeEnter() && !entries.isEmpty()) {
            entries.get(cursor.getIndex()).action().run();
        }
    }

    protected void drawEntries(Graphics2D g2,int centerX,int firstY,int step){
    for(int i=0;i<entries.size();i++){
        String label=entries.get(i).label();
        int x = UIHelper.getCenteredX(g2,centerX,label);
        int y =firstY+i*step;
        g2.drawString(label,x,y);
        if(i == cursor.getIndex()){
            g2.drawString(">",x- CURSOR_OFF_SET,y);
        }
    }


    }


}
