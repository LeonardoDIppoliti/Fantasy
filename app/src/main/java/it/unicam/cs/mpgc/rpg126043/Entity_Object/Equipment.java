package it.unicam.cs.mpgc.rpg126043.Entity_Object;

import it.unicam.cs.mpgc.rpg126043.Entity.Entity;
import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

public class Equipment extends Entity {
    private final int attackValue;
    private  final int defenseValue;

    public Equipment(GamePanel gP,String name,int attackValue,int defenseValue) {
        super(gP);
        this.name = name;
        this.attackValue = attackValue;
        this.defenseValue = defenseValue;
    }
    public int getAttackValue()
    {
        return attackValue;
    }
    public int getDefenseValue()
    {
        return defenseValue;
    }
}
