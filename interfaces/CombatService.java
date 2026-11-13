package it.unicam.cs.mpgc.rpg126043.interfaces;

import it.unicam.cs.mpgc.rpg126043.entity.monster.Monster;

public interface CombatService {
    void monsterTouchedPlayer(Monster monster);
    void playerHitMonster(Monster monster);
}
