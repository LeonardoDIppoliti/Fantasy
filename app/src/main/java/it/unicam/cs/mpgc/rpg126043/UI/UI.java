package it.unicam.cs.mpgc.rpg126043.UI;


import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.awt.*;


public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40;
    private int commandNum = 0;
    private int maxCommandNum;

    private enum gameState {titleScreen, playScreen, dialogueScreen, gameOverScreen, MenuScreen, controlsScreen, characterStatusScreen}

    private int gameStateIndex = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("arial", Font.PLAIN, 40);

    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        //TITLE STATE
        if (gameStateIndex == gameState.titleScreen.ordinal()) {
            maxCommandNum = 2;
            gp.getTitleScreen().drawTitleScreen(g2);
            gp.getGameScreenHandler().WSPressed();
            gp.getGameScreenHandler().enterCommandControl(gameState.titleScreen.ordinal());
        }

        //PLAY STATE
        if (gameStateIndex == gameState.playScreen.ordinal()) {
            gp.getPlayScreen().drawPlayerLife(g2);
            gp.getPlayScreen().drawMessage(g2);
            gp.getGameScreenHandler().escapeCommandControll(gameState.playScreen.ordinal());
        }

        //MENU STATE
        if (gameStateIndex == gameState.MenuScreen.ordinal()) {
            gp.getMenuScreen().drawMenuState(g2);
            maxCommandNum = 2;
            gp.getGameScreenHandler().WSPressed();
            gp.getGameScreenHandler().enterCommandControl(gameState.MenuScreen.ordinal());
            gp.getGameScreenHandler().escapeCommandControll(gameState.MenuScreen.ordinal());

        }
        //CONTROL STATE
        if (gameStateIndex == gameState.controlsScreen.ordinal()) {
            gp.getControlsScreen().drawControlsScreen(g2);
            gp.getGameScreenHandler().escapeCommandControll(gameState.controlsScreen.ordinal());
                   }
        //DIALOGUE STATE
        if (gameStateIndex == gameState.dialogueScreen.ordinal()) {
            gp.getDialogueScreen().drawDialogueScreen(g2);
            gp.getGameScreenHandler().enterCommandControl(gameState.dialogueScreen.ordinal());
        }
        //CHARACTER STATE
        if (gameStateIndex == gameState.characterStatusScreen.ordinal()) {
            gp.getCharacterStatusScreen().drawCharacterStatsScreen(g2);
            gp.getGameScreenHandler().escapeCommandControll(gameState.characterStatusScreen.ordinal());
        }
        //GAMEOVER STATE
        if (gameStateIndex == gameState.gameOverScreen.ordinal()) {
            gp.getGameOverScreen().drawGameOverScreen(g2);
            gp.getGameScreenHandler().WSPressed();
            gp.getGameScreenHandler().enterCommandControl(gameState.gameOverScreen.ordinal());
        }
    }


public void showGameOver(boolean win){
        commandNum=0;
        maxCommandNum = win ? 0:1;
    gp.keyH.setEscPressed(false);
    gp.keyH.setUpPressed(false);
    gp.keyH.setDownPressed(false);

    gameStateIndex=GameState.gameOverScreen.ordinal();
}

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }



    public int getXforCenteredText(String text) {
        int lenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - lenght / 2;
    }

    public int getXforAlignToRightText(String text, int tailX) {
        int lenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return tailX - lenght;
    }

    public int getCommandNum() {

        return commandNum;
    }
    public int getMaxCommandNum() {
        return maxCommandNum;
    }
    public int getMinCommandNum() {
        return 0;
    }

    public void setCommandNum(int num) {
        this.commandNum = num;
    }
    public int getGameStateIndex() {
        return gameStateIndex;
    }
    public void setGameStateIndex(int gameStateIndex) {
        this.gameStateIndex = gameStateIndex;
    }
    public enum GameState {
        titleScreen,playscreen, dialogueScreen, gameOverScreen, MenuScreen, controlsScreen, characterStatusScreen
    }
    public Graphics2D getG2() {
        return g2;
    }
}

