package it.unicam.cs.mpgc.rpg126043.Entity;

public class Stats {
    private int maxLife,attack,defense;
    public Stats(int maxLife,int attack,int defense){
        this.maxLife = maxLife;
        this.attack = attack;
        this.defense = defense;
    }

    public int getMaxLife() {
        return maxLife;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }
}
