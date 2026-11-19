package it.unicam.cs.mpgc.rpg126043.ui.screen;

import it.unicam.cs.mpgc.rpg126043.interfaces.InputState;
import it.unicam.cs.mpgc.rpg126043.interfaces.UIContext;
import it.unicam.cs.mpgc.rpg126043.ui.*;
import it.unicam.cs.mpgc.rpg126043.ui.component.SuperScreen;


import java.awt.*;
import java.util.List;
/** List of keyboard commands. */
public class ControlsScreen  extends SuperScreen {

    private static final List<String> LINES = List.of(
            "W = UP", "A = LEFT", "S = DOWN", "D = RIGHT",
            "ESC = MENU / BACK", "ENTER = INTERACT / ATTACK"
    );


    public ControlsScreen(UIContext uiContext, ScreenNavigator navi) {
      super(uiContext,navi);

    }

    @Override
    public void update(InputState input){
        if(input.consumeEsc())navi.goToScreen(ScreenId.MENU);
    }

    @Override
    public void draw(Graphics2D g2){
        int tile=uiContext.getTileSize();
        int screenX=tile*2;
        int screenY=tile;
        int screenWidth = tile*10;
        int screenHeight=tile*10;
        UIHelper.drawSubWindow(g2,screenX,screenY,screenWidth,screenHeight);


        //CHANGE
        screenX=tile*3;
        screenY=tile*2;
        g2.setColor(UITheme.COLOR_TEXT);
        g2.setFont(g2.getFont().deriveFont(UITheme.SIZE_BODY));
        g2.drawString("Controls",screenX,screenY);

        screenY=tile*4;
        for(String line: LINES){
            g2.drawString(line,screenX,screenY);
            screenY+=tile;
        }



    }


}
