package it.unicam.cs.mpgc.rpg126043.entity;

import it.unicam.cs.mpgc.rpg126043.interfaces.DialogueService;
import it.unicam.cs.mpgc.rpg126043.interfaces.EntityContext;

/** Non-player character who walks randomly and speaks when the player interacts with it. */
public abstract class NPC extends Entity {
    private final DialogueService dialogueService;
    private final String[] dialogue;
    private int dialogueIndex;
    private final RandomWalk walk = new RandomWalk();

    protected NPC(EntityContext entityContext, DialogueService dialogueService, String name, int speed, String... dialogue) {
        super(entityContext, name, speed);
        this.dialogueService = dialogueService;
        this.dialogue = dialogue;

    }
    @Override
    protected void setAction() {
        setDirection(walk.next(getDirection()));
    }

    public void speak(Direction playerFacing){
        if(dialogue.length == 0) {return;}
        dialogueService.show(dialogue[dialogueIndex++]);
        dialogueIndex = (dialogueIndex) % dialogue.length;
        setDirection(playerFacing.opposite()); //turn to player
    }
}
