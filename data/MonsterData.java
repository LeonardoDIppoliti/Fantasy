package it.unicam.cs.mpgc.rpg126043.data;

import java.io.Serializable;
/** State of a monster to be created or saved; currentLife = 0 means full life. */
public record MonsterData(String type,int worldX,int worldY,int currentLife) implements Serializable {
}
