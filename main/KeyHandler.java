package it.unicam.cs.mpgc.rpg126043.main;

import it.unicam.cs.mpgc.rpg126043.interfaces.InputState;
import it.unicam.cs.mpgc.rpg126043.interfaces.PlayerInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
/** Translates key inputs: "hold" flag for movement, "press once" events for menus and actions. */
public class KeyHandler implements KeyListener, InputState, PlayerInput {
    //PLAYER MOVEMENT INPUT
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    //SCREEN INPUT
    private boolean upEvent, downEvent, enterEvent, escEvent;
    private final Set<Integer> keysDown = new HashSet<>();

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    /*
    * It distinguishes two types of input for the same key.
    * The "press and hold" flag is used for movement.
    *  The "press once" event is used for menus, dialogs, and actions:
    * it only fires when the key is released and pressed,
    * so the keyboard's auto-repeat doesn't reactivate it.
    * */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        setHeld(code,true);
        if(!keysDown.add(code)){return;}
        switch (code) {
            case KeyEvent.VK_W:
                upEvent = true;
                break;
            case KeyEvent.VK_S:
                downEvent = true;
                break;
            case KeyEvent.VK_ENTER:
                enterEvent = true;
                break;
            case KeyEvent.VK_ESCAPE:
                escEvent =true;
                break;
            default:
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keysDown.remove(keyEvent.getKeyCode());
        setHeld(keyEvent.getKeyCode(),false);
    }

    private void setHeld(int code , boolean value){
        switch (code){
            case KeyEvent.VK_W -> upPressed = value;
            case KeyEvent.VK_S -> downPressed = value;
            case KeyEvent.VK_A -> leftPressed = value;
            case KeyEvent.VK_D -> rightPressed = value;
            default -> {}
        }
    }
    /*
     * Called when the window loses focus:
     * key releases would occur outside the panel and be lost
     * */
    public void releaseAll(){
        keysDown.clear();
        clearPressed();
        clearEvents();
    }

    /*
     * Reads and resets the event in one go ("consume"):
     *  each press is seen from only one side.
     * */
    @Override
    public boolean consumeUp() {
        boolean conUp= upEvent;
        upEvent = false;
        return conUp;
    }

    @Override
    public boolean consumeDown() {
        boolean conDown = downEvent;
        downEvent = false;
        return conDown;
    }

    @Override
    public boolean consumeEnter() {
      boolean conEnter = enterEvent;
      enterEvent = false;
      return conEnter;
    }

    @Override
    public boolean consumeEsc() {
        boolean conEsc = escEvent;
        escEvent = false;
        return conEsc;
    }

    @Override
    public void clearEvents() {
        upEvent = downEvent = enterEvent
        = escEvent = false;
    }
    private void clearPressed(){
        upPressed=downPressed=leftPressed=rightPressed=false;
    }


    //Player input
    @Override
    public boolean isUpPressed() {
        return upPressed;
    }
    @Override
    public boolean isDownPressed() {
        return downPressed;
    }
    @Override
    public boolean isLeftPressed() {
        return leftPressed;
    }
    @Override
    public boolean isRightPressed() {
        return rightPressed;
    }
    @Override
    public boolean hasAction(){return enterEvent;}
    @Override
    public boolean consumeAction(){
    return consumeEnter();}
}