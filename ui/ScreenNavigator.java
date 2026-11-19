package it.unicam.cs.mpgc.rpg126043.ui;

import it.unicam.cs.mpgc.rpg126043.interfaces.InputState;
import it.unicam.cs.mpgc.rpg126043.ui.component.SuperScreen;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
/** Records screens and handles switching between them. */
public class ScreenNavigator {
    private final Map<ScreenId, SuperScreen> screens = new HashMap<>();
    private final InputState input;
    private SuperScreen currentScreen;
    private ScreenId currentId;


    public ScreenNavigator(InputState input) {
        this.input = input;
    }

    public void registerScreen(ScreenId id , SuperScreen screen){
        screens.put(id,screen);
    }

    /*
    * Change screen by discarding pending input events:
    * without this,the ENTER that opened a dialogue would be read immediately by the next screen
    * and would close it at the same time.
    * */
    public void goToScreen(ScreenId id){
        SuperScreen nextScreen = screens.get(id);
        if(nextScreen != null) {
            input.clearEvents();
            nextScreen.onEnter();
            currentScreen = nextScreen;
            currentId=id;
        }

    }

    public void update(){
        if(currentScreen!=null){
            currentScreen.update(input);
        }
    }
    public void draw(Graphics2D g2){
        if(currentScreen!=null){
            currentScreen.draw(g2);
        }
    }

    public ScreenId getId(){
        return currentId;
    }


    public boolean isPlaying(){
        return currentId != ScreenId.PLAY;
    }

}
