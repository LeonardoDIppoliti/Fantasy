package it.unicam.cs.mpgc.rpg126043.ui.screen;

import it.unicam.cs.mpgc.rpg126043.interfaces.DialogueService;
import it.unicam.cs.mpgc.rpg126043.interfaces.InputState;
import it.unicam.cs.mpgc.rpg126043.interfaces.UIContext;
import it.unicam.cs.mpgc.rpg126043.ui.*;
import it.unicam.cs.mpgc.rpg126043.ui.component.SuperScreen;

import java.awt.*;
/** Dialog box with multi-line text, closed by ENTER. */
public class DialogueScreen  extends SuperScreen implements DialogueService {
    private static final int MAX_LINES = 3;
    private String text = " ";

    public DialogueScreen(UIContext uiContext, ScreenNavigator navi) {
        super(uiContext, navi);

    }

    @Override
    public void show(String text){
        this.text=text;
        navi.goToScreen(ScreenId.DIALOGUE);
    }

    @Override
    public void update(InputState input) {
        if(input.consumeEnter()) navi.goToScreen(ScreenId.PLAY);
    }

    @Override
    public void draw(Graphics2D g2) {
        int tile= uiContext.getTileSize();
        int screenX=tile*2;
        int screenY=tile/2;
        int screenWidth = uiContext.getScreenWidth()-tile*4;
        int screenHeight=tile*4;
        UIHelper.drawSubWindow(g2,screenX,screenY, screenWidth, screenHeight);

        g2.setColor(UITheme.COLOR_TEXT);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,UITheme.SIZE_DIALOGUE));
        screenY=tile/2 + tile;
        //CHANGE
        screenX=tile*3;
        String[] lines = text.split("\n");
        for(int i = 0; i<Math.min(lines.length, MAX_LINES); i++){
            g2.drawString(lines[i],screenX,screenY);
            screenY+=tile;
        }

    }


}
