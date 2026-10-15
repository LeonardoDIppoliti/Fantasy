package it.unicam.cs.mpgc.rpg126043.Main;

import it.unicam.cs.mpgc.rpg126043.Entity.Entity;
import it.unicam.cs.mpgc.rpg126043.Entity.Player;
import it.unicam.cs.mpgc.rpg126043.UI.UI;

public class CombatHandler {
    private GamePanel gP;
    public CombatHandler(GamePanel gP) {
        this.gP = gP;
    }


    public void damageMonster(int i){
        Player player = gP.player;
        if (i != 999) {
            Entity monster = gP.monster[i];
            if (!monster.invincible) {

             int damage = calculateDamage(monster,gP.player);
               monster.currentLife -= damage;
                gP.combatHandler.addMessage(damage+" damage!");
                monster.invincible = true;
                monster.damageReaction();

                if (monster.currentLife <= 0) {
                    monster.setDying(true);
                    addMessage("Killed the " +monster.name + "!");
                    addMessage("Obtained " + monster.getExp() + " EXP!");
                    player.setExp(player.getExp() + monster.getExp() );

                    player.checkLevelUp();
                }
            }
        }
    }
    public void contactMonster(Entity monster){

            Player player = gP.player;
           if(monster != null && monster.isAlive() && !monster.invincible){
             if (!player.getInvincible() && gP.ui.getGameStateIndex()==UI.GameState.playscreen.ordinal()) {
                int damage = calculateDamage(player,monster);
                player.setCurrentLife(player.getCurrentLife() - damage);
                player.setInvincible(true);
                }
           }


    }
    public void addMessage(String message) {
        gP.getPlayScreen().getMessageList().add(message);
        gP.getPlayScreen().getMessageCounter().add(0);
    }

    private int calculateDamage(Entity defender,Entity attacker){
        return Math.max(0,attacker.attack-defender.defense);
    }
}
