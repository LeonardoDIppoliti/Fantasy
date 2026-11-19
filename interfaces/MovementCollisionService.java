package it.unicam.cs.mpgc.rpg126043.interfaces;

import it.unicam.cs.mpgc.rpg126043.entity.Entity;

public interface MovementCollisionService {
   int NOT_HIT =-1;
   static boolean isHit(int index){return index != NOT_HIT;}

    void checkTile(Entity entity);
    int checkNpc(Entity entity);
    int checkMonster(Entity entity);
    boolean checkPlayer(Entity entity);
}
