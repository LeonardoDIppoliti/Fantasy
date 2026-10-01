package it.unicam.cs.mpgc.rpg126043.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gP;
    public boolean upPressed,downPressed,leftPressed,rightPressed,enterPressed;

    public KeyHandler(GamePanel gP){
        this.gP=gP;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        if (gP.gameState == gP.playState) {

            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }

            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_P) {
                gP.gameState = gP.pauseState;
            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }


        }

        //PAUSE STATE
        else if (gP.gameState == gP.pauseState) {
            if (code ==  KeyEvent.VK_P) {
                gP.gameState = gP.playState;
            }

        }

        //DIALOGUE STATE
        else if (gP.gameState == gP.dialogueState) {
            if(code ==  KeyEvent.VK_ENTER) {
                gP.gameState = gP.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
