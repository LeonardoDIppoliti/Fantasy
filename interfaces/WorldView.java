package it.unicam.cs.mpgc.rpg126043.interfaces;

import it.unicam.cs.mpgc.rpg126043.entity.NPC;
import it.unicam.cs.mpgc.rpg126043.entity.monster.Monster;

public interface WorldView extends PlayerProvider{
    NPC[] getNpc();
    Monster[] getMonster();
}
