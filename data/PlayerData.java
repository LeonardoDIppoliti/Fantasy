package it.unicam.cs.mpgc.rpg126043.data;

import java.io.Serializable;
/** State of the player to be saved. */
public record PlayerData(int level,
        int maxLife,
        int currentLife,
        int defense,
        int attack,
        int exp,
        int nextLevelExp,
        int worldX,
        int worldY) implements Serializable {
}
