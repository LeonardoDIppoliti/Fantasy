package it.unicam.cs.mpgc.rpg126043.entity;

import it.unicam.cs.mpgc.rpg126043.interfaces.DialogueService;
import it.unicam.cs.mpgc.rpg126043.interfaces.EntityContext;
/**NPC with his own dialogue and sprites. */
public class OldManNPC extends NPC {
    public OldManNPC(EntityContext entityContext, DialogueService dialogueService) {
        super(entityContext,dialogueService,"Old Man",1,"Hello, young man...","Thank you for being here",
                "Slimes were spotted around here...","Please defeat them all");
       sprites=loadDirectional("/NPC/oldman_");
    }

}