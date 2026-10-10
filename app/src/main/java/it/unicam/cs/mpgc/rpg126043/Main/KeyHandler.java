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

        //TITLE STATE
        if (gP.gameState == gP.titleState) {
            titleState(code);
        }
        //PLAY STATE
        else if (gP.gameState == gP.playState) {
            playState(code);
        }

        //PAUSE STATE
        else if (gP.gameState == gP.pauseState) {
            pauseState(code);
        }

        //DIALOGUE STATE
        else if (gP.gameState == gP.dialogueState) {
            dialogueState(code);
        }
        //CHARACTER STATE
        else if (gP.gameState == gP.characterState) {
            characterState(code);
        }
        //CHARACTER STATE
        else if (gP.gameState == gP.optionState) {
            optionState(code);
        }

    }
    public void optionState(int code){
        if(code==KeyEvent.VK_ESCAPE){
            gP.gameState=gP.playState;
        }
        if(code==KeyEvent.VK_ENTER){
            enterPressed=true;
        }
    }
    public void titleState(int code){
        if(gP.ui.titleScreenState == 0) {

            if (code == KeyEvent.VK_W) {
                gP.ui.commandNum--;
                if (gP.ui.commandNum < 0) {
                    gP.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gP.ui.commandNum++;
                if (gP.ui.commandNum > 2) {
                    gP.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE){
                if(gP.ui.commandNum==0){
                    gP.ui.titleScreenState=1;
                }
                if(gP.ui.commandNum==1){
                    //ADD LATER
                }
                if(gP.ui.commandNum==2){
                    System.exit(0);
                }
            }
        }
        else if(gP.ui.titleScreenState == 1) {
            if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
                gP.gameState = gP.playState;
            }
        }
    }
    public void playState(int code){

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
        if(code == KeyEvent.VK_C){
            gP.ui.memberNum=0;
            gP.gameState = gP.characterState;
        }
        if(code == KeyEvent.VK_ESCAPE){
            gP.gameState = gP.optionState;
        }


    }
    public void pauseState(int code){
        if (code ==  KeyEvent.VK_P) {
            gP.gameState = gP.playState;
        }
    }
    public void dialogueState(int code){
        if(code ==  KeyEvent.VK_ENTER) {
            gP.gameState = gP.playState;
        }
    }
    public void characterState(int code){
        if (code == KeyEvent.VK_A) {
            gP.ui.memberNum--;
            if (gP.ui.memberNum < 0) {
                gP.ui.memberNum = gP.pHandler.getPartySize()-1;
            }
        }
        if (code == KeyEvent.VK_D) {
            gP.ui.memberNum++;
            if (gP.ui.memberNum >= gP.pHandler.getPartySize()) {
                gP.ui.memberNum = 0;
            }
        }
        if(code ==  KeyEvent.VK_C) {
            gP.gameState = gP.playState;
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
