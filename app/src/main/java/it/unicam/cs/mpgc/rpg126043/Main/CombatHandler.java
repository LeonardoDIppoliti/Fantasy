package it.unicam.cs.mpgc.rpg126043.Main;

import it.unicam.cs.mpgc.rpg126043.Entity.Entity;
import it.unicam.cs.mpgc.rpg126043.Entity.Player;
import it.unicam.cs.mpgc.rpg126043.Monster.Monster;
import it.unicam.cs.mpgc.rpg126043.UI.UI;

public class CombatHandler {
    private GamePanel gP;
    public CombatHandler(GamePanel gP) {
        this.gP = gP;
    }


    public void damageMonster(int i){
        Player player = gP.player;
        Monster monster = gP.monster[i];
        int damage=player.damageAgainst(monster);
        if(!monster.takeDamage(damage)) {return;}
        addMessage(damage + " damage!");
        monster.damageReaction();
         if(monster.isDefeated()){
             monster.startDying();
             addMessage("Killed the "+ monster.getName()+" !");
             addMessage("Obtained " + monster.getRewardExp() +" !");
             if(player.checkLevelUp(monster.getRewardExp())){
                 gP.getDialogueScreen().setCurrentDialogue(
                         "You feel stronger! \n" + "You are level : " +gP.player.getLevel()+" now !"
                 );
                 gP.ui.setGameStateIndex(UI.GameState.dialogueScreen.ordinal());
             }

         }

    }
    public void contactMonster(Monster monster){
    if(monster == null|| !monster.isAlive() || monster.isDying() || monster.isInvincible()){    return;}
    if(gP.ui.getGameStateIndex() != UI.GameState.playscreen.ordinal()) {return;}
    gP.player.takeDamage(monster.damageAgainst(gP.player));
    }
    public void addMessage(String message) {
        gP.getPlayScreen().getMessageList().add(message);
        gP.getPlayScreen().getMessageCounter().add(0);
    }

}
