package it.unicam.cs.mpgc.rpg126043.ui.screen;

import it.unicam.cs.mpgc.rpg126043.interfaces.GameSession;
import it.unicam.cs.mpgc.rpg126043.interfaces.UIContext;
import it.unicam.cs.mpgc.rpg126043.ui.*;
import it.unicam.cs.mpgc.rpg126043.ui.component.MenuEntry;

import java.awt.*;
import java.util.List;
/** Options menu opened with ESC. */
public class MenuScreen  extends ChoiceScreen {
    public MenuScreen(UIContext uiContext, ScreenNavigator nav, GameSession session) {
        super(uiContext,nav);
        setMenuEntries(List.of(
                new MenuEntry("STATUS",()->nav.goToScreen(ScreenId.STATUS)),
                new MenuEntry("CONTROLS",()->nav.goToScreen(ScreenId.CONTROLS)),
                new MenuEntry("END GAME",session::quit)
        ));
    }
    @Override
    protected void onBack(){
        navi.goToScreen(ScreenId.PLAY);
    }

    @Override
    public void draw(Graphics2D g2) {
        int tile=uiContext.getTileSize();
        int frameX=tile*6;
        int width=tile*8;
        int height=tile*10;
        UIHelper.drawSubWindow(g2,frameX, tile,width,height);

        g2.setColor(UITheme.COLOR_TEXT);
        g2.setFont(g2.getFont().deriveFont(UITheme.SIZE_BODY));
        int centerX= frameX+width/2;
        int y=tile*2;
        String title ="OPTIONS";

        g2.drawString(title,UIHelper.getCenteredX(g2,centerX,title), y);
        drawEntries(g2,centerX, y*2,tile);
    }
}