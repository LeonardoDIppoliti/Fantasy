package it.unicam.cs.mpgc.rpg126043.ui.screen;

import it.unicam.cs.mpgc.rpg126043.entity.Direction;
import it.unicam.cs.mpgc.rpg126043.interfaces.GameSession;
import it.unicam.cs.mpgc.rpg126043.interfaces.UIContext;
import it.unicam.cs.mpgc.rpg126043.ui.ScreenNavigator;
import it.unicam.cs.mpgc.rpg126043.ui.UIHelper;
import it.unicam.cs.mpgc.rpg126043.ui.UITheme;
import it.unicam.cs.mpgc.rpg126043.ui.component.MenuEntry;

import java.awt.*;
import java.util.List;
/** Title screen with START GAME, LOAD GAME, and QUIT options. */
public class TitleScreen  extends ChoiceScreen {

    public TitleScreen(UIContext uiContext, ScreenNavigator nav, GameSession gameSession) {
        super(uiContext,nav);
        setMenuEntries(List.of(
                new MenuEntry("START GAME", gameSession::newGame),
                new MenuEntry("LOAD GAME", gameSession::loadGame),
                new MenuEntry("QUIT", gameSession::quit)
        ));
    }

    @Override
    public void draw(Graphics2D g2) {
        int tile = uiContext.getTileSize();
        int centerX=uiContext.getScreenWidth()/2;
        Font base = UITheme.BASE_FONT;
        g2.setColor(UITheme.COLOR_TITLE_BACKGROUND);
        g2.fillRect(0,0,uiContext.getScreenWidth(),uiContext.getScreenHeight());

        g2.setFont(base.deriveFont(Font.BOLD,UITheme.SIZE_GAME_TITLE));
        String title ="FANTASY";
        int screenY=tile*3;
        UIHelper.drawShadowed(g2,title,UIHelper.getCenteredX(g2,centerX,title),screenY,UITheme.SHADOW_TITLE);

        //PLAYER IMAGE CENTERED
        int y=tile*5,width=tile*2,height=tile*2;
        g2.drawImage(uiContext.getPlayer().getSprites().get(Direction.DOWN,1),
                centerX-tile,y,width,height,null);

        g2.setFont(base.deriveFont(Font.BOLD,UITheme.SIZE_TITLE_BODY));
        g2.setColor(UITheme.COLOR_TEXT);
        drawEntries(g2,centerX, (int) ( tile*8.5),tile);

    }
}