package it.unicam.cs.mpgc.rpg126043.main;

import it.unicam.cs.mpgc.rpg126043.data.SaveLoad;
import it.unicam.cs.mpgc.rpg126043.entity.Player;
import it.unicam.cs.mpgc.rpg126043.interfaces.GameSession;
import it.unicam.cs.mpgc.rpg126043.ui.component.MessageLog;
import it.unicam.cs.mpgc.rpg126043.ui.ScreenId;
import it.unicam.cs.mpgc.rpg126043.ui.ScreenNavigator;

import java.util.function.Supplier;
/** Session transitions: new game, load, retry, return to title, exit. */
public class GameFlow implements GameSession {
    private final Supplier<Player> player;
    private final ScreenNavigator navi;
    private final AssetSetter assetSetter;
    private final SaveLoad saveLoad;
    private final MessageLog messageLog;

    public GameFlow(Supplier<Player> player, ScreenNavigator navi,
                    AssetSetter assetSetter, SaveLoad saveLoad, MessageLog messageLog) {
        this.player = player;
        this.navi = navi;
        this.assetSetter = assetSetter;
        this.saveLoad = saveLoad;
        this.messageLog = messageLog;
    }

    public void resetGame(){
        populateWorld();
        messageLog.clear();
        player.get().setDefaultValues();
    }

    public void populateWorld(){
        assetSetter.setNPC();
        assetSetter.setMonster();

    }


    @Override
    public void newGame() {
        resetGame();
        navi.goToScreen(ScreenId.PLAY);
    }

    @Override
    public boolean loadGame() {
        if(!saveLoad.load()){return false;}
        navi.goToScreen(ScreenId.PLAY);
        return true;
    }

    @Override
    public void retry() {
        player.get().setDefaultPosition();
        player.get().restoreStatus();
        navi.goToScreen(ScreenId.PLAY);
    }

    @Override
    public void backToTitle() {
        resetGame();
        navi.goToScreen(ScreenId.TITLE);
    }

    @Override
    public void quit() {
        System.exit(0);
    }
}
