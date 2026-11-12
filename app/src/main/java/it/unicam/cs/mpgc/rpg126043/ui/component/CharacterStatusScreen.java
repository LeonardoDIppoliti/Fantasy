package it.unicam.cs.mpgc.rpg126043.ui.component;

import it.unicam.cs.mpgc.rpg126043.interfaces.InputState;
import it.unicam.cs.mpgc.rpg126043.interfaces.UIContext;
import it.unicam.cs.mpgc.rpg126043.ui.*;

import java.awt.*;
import java.util.List;
import java.util.function.Supplier;
/** Player statistics, one line per value. */
public class CharacterStatusScreen extends SuperScreen {
    private static final int LINE_HEIGHT = 35;
    private static final int FRAME_PADDING = 20;


    private record Row(String label, Supplier<String> value){}
    private final List<Row> rows;

    public CharacterStatusScreen(UIContext uiContext, ScreenNavigator navi) {
        super(uiContext,navi);
        rows = List.of(
                new Row("Name",() -> uiContext.getPlayer().getName()),
                new Row("Level",()-> String.valueOf(uiContext.getPlayer().getLevel())),
                new Row("Life",()->uiContext.getPlayer().getCurrentLife() +"/"+uiContext.getPlayer().getMaxLife()),
                new Row("Attack",()-> String.valueOf(uiContext.getPlayer().getAttack())),
                new Row("Defense",()-> String.valueOf(uiContext.getPlayer().getDefense())),
                new Row("Exp",()-> String.valueOf(uiContext.getPlayer().getExp())),
                new Row("Next Level Exp",()-> String.valueOf(uiContext.getPlayer().getNextLevelExp()))
        );
    }
    @Override
    public void update(InputState input) {
        if(input.consumeEsc()) navi.goToScreen(ScreenId.MENU);
    }

    @Override
    public void draw(Graphics2D g2) {
        int tile = uiContext.getTileSize();
        int frameX=tile*2;
        int width=tile*7;
        int height=tile*10;
        UIHelper.drawSubWindow(g2,frameX, tile,width,height);

        g2.setColor(UITheme.COLOR_TEXT);
        g2.setFont(g2.getFont().deriveFont(UITheme.SIZE_STATUS));
        int tailX=frameX+width-30;
        int tailY= tile +tile;

        for(Row r:rows){
            String value = r.value().get();
            g2.drawString(r.label(),frameX+ FRAME_PADDING,tailY);
            g2.drawString(value,UIHelper.getAlignToRightX(g2,value,tailX),tailY);
            tailY+= LINE_HEIGHT;

        }


    }
}


