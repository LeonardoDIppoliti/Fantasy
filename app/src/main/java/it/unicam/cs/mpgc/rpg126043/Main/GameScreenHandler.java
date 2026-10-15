package it.unicam.cs.mpgc.rpg126043.Main;

import it.unicam.cs.mpgc.rpg126043.UI.UI;

public class GameScreenHandler {
    GamePanel gP;
  
    public GameScreenHandler(GamePanel gP) {
        this.gP = gP;
    }


    public void WSPressed(){
      if(gP.keyH.isUpPressed()){
          gP.ui.setCommandNum(gP.ui.getCommandNum()-1);
          if(gP.ui.getCommandNum() < gP.ui.getMinCommandNum()){
          gP.ui.setCommandNum(gP.ui.getMaxCommandNum());
          }
          gP.keyH.setUpPressed(false);
      }
      if(gP.keyH.isDownPressed()){
          gP.ui.setCommandNum(gP.ui.getCommandNum()+1);
          if(gP.ui.getCommandNum() > gP.ui.getMaxCommandNum()){
              gP.ui.setCommandNum(gP.ui.getMinCommandNum());
          }
          gP.keyH.setDownPressed(false);
      }
    }
    public void enterCommandControl(int gameState){
        if(gP.keyH.isEnterPressed()){

            if(gameState == UI.GameState.titleScreen.ordinal()){
                if (gP.ui.getCommandNum()== gP.ui.getMinCommandNum()) {

                    gP.keyH.setEnterPressed(false);
                    gP.ui.setGameStateIndex(UI.GameState.playscreen.ordinal());
                    gP.ui.setCommandNum(0);

                }
                if(gP.ui.getCommandNum() == 1){
                    gP.keyH.setEnterPressed(false);
                    gP.getSaveLoad().load();
                    gP.ui.setGameStateIndex(UI.GameState.playscreen.ordinal());
                    gP.ui.setCommandNum(0);
                }

                if(gP.ui.getCommandNum() == gP.ui.getMaxCommandNum()){
                    gP.resetGame();

                    closeGame();
                }
            }
            if(gameState == UI.GameState.MenuScreen.ordinal()){
               gP.keyH.setEnterPressed(false);
                if(gP.ui.getCommandNum() == 0){
                    gP.ui.setGameStateIndex(UI.GameState.characterStatusScreen.ordinal());
                    gP.ui.setCommandNum(0);

                }
                if(gP.ui.getCommandNum() == 1){
                    gP.ui.setGameStateIndex(UI.GameState.controlsScreen.ordinal());
                    gP.ui.setCommandNum(0);

                }
                if(gP.ui.getCommandNum() == 2){
                    gP.resetGame();
                    closeGame();
                }
            }
            if (gameState == UI.GameState.dialogueScreen.ordinal()) {
                gP.ui.setGameStateIndex(UI.GameState.playscreen.ordinal());
                gP.keyH.setEnterPressed(false);
            }
            if (gameState == UI.GameState.gameOverScreen.ordinal()) {
                gP.keyH.setEnterPressed(false);

                if (!gP.gameFinished()) {
                    if (gP.ui.getCommandNum() == gP.ui.getMinCommandNum()) {
                        gP.player.restoreStatus();
                        gP.player.setDefaultPosition();
                        gP.ui.setGameStateIndex(UI.GameState.playscreen.ordinal());
                        gP.ui.setCommandNum(0);
                    }
                    if (gP.ui.getCommandNum() == gP.ui.getMaxCommandNum()) {
                        gP.resetGame();

                        gP.ui.setGameStateIndex(UI.GameState.titleScreen.ordinal());
                        gP.ui.setCommandNum(0);
                    }
                } else {
                    closeGame();
                }

            }

        }
    }
    public void escapeCommandControll(int gameState){
        if(gP.keyH.isEscPressed()){
            if(gameState == UI.GameState.playscreen.ordinal()){

               gP.ui.setGameStateIndex(UI.GameState.MenuScreen.ordinal());
                gP.ui.setCommandNum(0);
               gP.keyH.setEscPressed(false);
            }
            if(gameState == UI.GameState.controlsScreen.ordinal()){
                gP.keyH.setEscPressed(false);
                gP.ui.setGameStateIndex(UI.GameState.MenuScreen.ordinal());
            }
            if(gameState == UI.GameState.characterStatusScreen.ordinal()){
                gP.keyH.setEscPressed(false);
                gP.ui.setGameStateIndex(UI.GameState.MenuScreen.ordinal());
            }
            if(gameState == UI.GameState.MenuScreen.ordinal()){
                gP.ui.setGameStateIndex(UI.GameState.playscreen.ordinal());
                gP.ui.setCommandNum(0);
                gP.keyH.setEscPressed(false);
            }
        }
    }

    private void closeGame(){
        System.exit(0);
    }

}
