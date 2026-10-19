package it.unicam.cs.mpgc.rpg126043.Entity;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.awt.*;

public abstract class NPC extends Entity {
    private final String[] dialogue;
    private int dialogueIndex;
    private final RandomWalk walk = new RandomWalk();

    protected NPC(GamePanel gP, String name, int speed,String... dialogue) {
        super(gP, name, speed);
        this.dialogue = dialogue;
    }
    @Override
    protected void setAction() {
        setDirection(walk.next(getDirection()));
    }

    public void speak(){
        gP.getDialogueScreen().setCurrentDialogue(dialogue[dialogueIndex]);
        dialogueIndex = (dialogueIndex+1) % dialogue.length;
        setDirection(gP.player.getDirection().opposite()); //turn to player
    }
}
