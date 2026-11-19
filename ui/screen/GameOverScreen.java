package it.unicam.cs.mpgc.rpg126043.ui.screen;

import it.unicam.cs.mpgc.rpg126043.interfaces.GameSession;
import it.unicam.cs.mpgc.rpg126043.interfaces.UIContext;
import it.unicam.cs.mpgc.rpg126043.ui.*;
import it.unicam.cs.mpgc.rpg126043.ui.component.MenuEntry;

import java.awt.*;
import java.util.List;
/** Final screen: victory or defeat, with different entries depending on the outcome. */
public class GameOverScreen extends ChoiceScreen {

    private final GameSession gameSession;
    private boolean won;
    public GameOverScreen(UIContext uiContext, ScreenNavigator nav, GameSession session) {
       super(uiContext,nav);
       this.gameSession=session;
        setMenuEntries(entriesFor(false));
    }
    //DECIDE TITLE AND ENTRIES
    /*
    * Single entry point: set entries and title based on the outcome.
    * If you win, only "Quit" remains.
    * */
    public void show(boolean won){
        this.won=won;
        setMenuEntries(entriesFor(won));

        navi.goToScreen(ScreenId.GAME_OVER);
    }
    private List<MenuEntry> entriesFor (boolean won){
        return won
                ? List.of(new MenuEntry("Quit", gameSession::quit))
                : List.of(new MenuEntry("Retry", gameSession::retry),
                          new MenuEntry("Main Menu", gameSession::backToTitle));

    }


    @Override
    public void draw(Graphics2D g2) {
        int tile = uiContext.getTileSize();
        int centerX = uiContext.getScreenWidth()/2;
        Font base = UITheme.BASE_FONT;


        g2.setColor(UITheme.COLOR_OVERLAY);
        g2.fillRect(0,0,uiContext.getScreenWidth(),uiContext.getScreenHeight());

        g2.setFont(base.deriveFont(Font.BOLD,UITheme.SIZE_GAME_OVER_TITLE));
        String title = won ? "YOU WIN!":"GAME OVER!";
        UIHelper.drawShadowed(g2,title,UIHelper.getCenteredX(g2,centerX,title),tile*4,UITheme.SHADOW_GAME_OVER);

        g2.setFont(base.deriveFont(UITheme.SIZE_GAME_OVER_BODY));
        drawEntries(g2,centerX,tile*7,60);


    }
}
