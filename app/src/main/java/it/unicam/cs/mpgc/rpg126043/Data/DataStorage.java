package it.unicam.cs.mpgc.rpg126043.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataStorage implements Serializable {
    //PLAYER STATS
    int level;
    int maxLife;
    int currentLife;
    int defense;
    int attack;
    int exp;
    int nextLevelExp;
    int worldX;
    int worldY;

    //MONSTER STASTS
    List<MonsterData> monsters = new ArrayList<>();
}
