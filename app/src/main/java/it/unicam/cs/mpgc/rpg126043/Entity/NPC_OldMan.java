package it.unicam.cs.mpgc.rpg126043.Entity;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

public class NPC_OldMan extends NPC {
    public NPC_OldMan(GamePanel gP) {
        super(gP,"Old Man",1,"Hello, young man...","Thank you for being here",
                "Slimes were spotted around here...","Please defeat them all");
       sprites=loadDirectional("/NPC/oldman_");
    }

}