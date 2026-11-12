package it.unicam.cs.mpgc.rpg126043.ui;

import it.unicam.cs.mpgc.rpg126043.interfaces.CombatListener;
import it.unicam.cs.mpgc.rpg126043.ui.component.MessageLog;
import it.unicam.cs.mpgc.rpg126043.ui.screen.DialogueScreen;

/** Translates combat events into messages and dialogs. */
public class CombatFeedback implements CombatListener {
    private final MessageLog log;
    private final DialogueScreen dialogueScreen;

    public CombatFeedback(MessageLog log,DialogueScreen dialogueScreen){
        this.log=log;
        this.dialogueScreen=dialogueScreen;
    }

    @Override
    public void damageDealt(int damage){
        log.addMessage("Dealt " + damage + " damage!");
    }
    @Override
    public void monsterKilled(String monsterName,int rewardExp){
        log.addMessage("Killed the " + monsterName + "!");
        log.addMessage("Received " + rewardExp + " EXP!");
    }
    @Override
    public void playerLeveledUp(int level){
        dialogueScreen.show("You feel stronger \n You are level " + level + " now!");
    }
}
