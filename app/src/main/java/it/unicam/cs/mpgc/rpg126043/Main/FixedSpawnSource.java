package it.unicam.cs.mpgc.rpg126043.Main;

import it.unicam.cs.mpgc.rpg126043.Data.MonsterData;
import it.unicam.cs.mpgc.rpg126043.Interface.SpawnSource;

import java.util.Arrays;
import java.util.List;

public class FixedSpawnSource implements SpawnSource {
    private record spawn (String name,int col, int row){}

    private List<spawn> spawns = List.of(
            new spawn("slime",23,36),
            new spawn("slime",20,37),
            new spawn("slime",8,31),
            new spawn("slime",10,31),
            new spawn("slime",38,11),
            new spawn("slime",35,7),
            new spawn("slime",35,37),
            new spawn("slime",35,33)
    );

    private final int tileSize;
    public FixedSpawnSource(int tileSize) {
        this.tileSize = tileSize;
    }

    @Override
    public List<MonsterData> createSpawns() {
        return spawns.stream()
                .map(m -> new MonsterData(m.name,m.col*tileSize,m.row*tileSize,0))
                .toList();
    }
}
