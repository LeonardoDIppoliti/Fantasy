package it.unicam.cs.mpgc.rpg126043.main;

import it.unicam.cs.mpgc.rpg126043.data.MonsterData;
import it.unicam.cs.mpgc.rpg126043.interfaces.SpawnSource;
import it.unicam.cs.mpgc.rpg126043.entity.monster.MonsterRegistry;
import java.util.List;
/** Spawn source with fixed positions hardcoded. */
public class FixedSpawnSource implements SpawnSource {
    private record Spawn(String name,int col, int row){}

    private final List<Spawn> spawns = List.of(
            new Spawn(MonsterRegistry.SLIME,23,36),
            new Spawn(MonsterRegistry.SLIME,20,37),
            new Spawn(MonsterRegistry.SLIME,8,31),
            new Spawn(MonsterRegistry.SLIME,10,31),
            new Spawn(MonsterRegistry.SLIME,38,11),
            new Spawn(MonsterRegistry.SLIME,35,7),
            new Spawn(MonsterRegistry.SLIME,35,37),
            new Spawn(MonsterRegistry.SLIME,35,33)
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
