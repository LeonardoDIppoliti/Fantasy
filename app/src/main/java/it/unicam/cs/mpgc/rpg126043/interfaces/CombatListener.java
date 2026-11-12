package it.unicam.cs.mpgc.rpg126043.interfaces;

public interface CombatListener {
    default void damageDealt(int damage){}
    default void monsterKilled(String monsterName,int rewardExp){}
    default void playerLeveledUp(int newLevel){}
}
