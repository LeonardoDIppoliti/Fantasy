package it.unicam.cs.mpgc.rpg126043.data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
/** Contents of a save file: state of the player and living monsters. */
public class DataStorage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final PlayerData playerData;

    //MONSTER STASTS
    private final List<MonsterData> monsters;

    public DataStorage(PlayerData playerData,List<MonsterData> monsters) {
        this.playerData = playerData;
        this.monsters=monsters;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public List<MonsterData> getMonsters() {
        return monsters;
    }
}
