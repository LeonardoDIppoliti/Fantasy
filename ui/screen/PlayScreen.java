package it.unicam.cs.mpgc.rpg126043.ui.screen;

import it.unicam.cs.mpgc.rpg126043.entity.Player;
import it.unicam.cs.mpgc.rpg126043.interfaces.InputState;
import it.unicam.cs.mpgc.rpg126043.interfaces.UIContext;
import it.unicam.cs.mpgc.rpg126043.ui.*;
import it.unicam.cs.mpgc.rpg126043.ui.component.HealthBar;
import it.unicam.cs.mpgc.rpg126043.ui.component.MessageLog;
import it.unicam.cs.mpgc.rpg126043.ui.component.SuperScreen;

import java.awt.*;
/** Game screen: player health bar and combat messages. */
public class PlayScreen  extends SuperScreen {

    private static final int MESSAGE_STEP =50, BAR_OFFSET =15, BAR_HEIGHT = 10;
    private final MessageLog log;

    public PlayScreen(UIContext uiContext, ScreenNavigator navi, MessageLog log) {

        super(uiContext,navi);
        this.log=log;
    }
   @Override
    public void update(InputState input){
        log.tick();
        if(input.consumeEsc()) navi.goToScreen(ScreenId.MENU);
   }

   @Override
    public void draw(Graphics2D g2) {
        drawHUD(g2);
        drawMessages(g2);
   }

   private void drawHUD(Graphics2D g2) {
       int tile = uiContext.getTileSize();
       Player player = uiContext.getPlayer();
        //PLAYER NAME
        g2.setColor(UITheme.COLOR_TEXT);
        int x=tile/2;
        g2.drawString(player.getName(),x,tile);
        //HP BAR
       HealthBar.drawBar(g2,x+tile*3,tile - BAR_OFFSET,uiContext.getTileSize(), BAR_HEIGHT,
               player.getCurrentLife(),player.getMaxLife());

   }

    private void drawMessages(Graphics2D g2) {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,UITheme.SIZE_BODY));
        int y=uiContext.getTileSize()*4;;
        for(String text:log.texts()){
            UIHelper.drawShadowed(g2,text,uiContext.getTileSize(),y,UITheme.SHADOW_PLAY);
            y+= MESSAGE_STEP;

        }
    }

}
