package it.unicam.cs.mpgc.rpg126043.Data;

import java.io.Serializable;

public record MonsterData(String type,int worldX,int worldY,int currentLife) implements Serializable {
}
