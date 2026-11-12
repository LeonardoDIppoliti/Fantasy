package it.unicam.cs.mpgc.rpg126043.interfaces;

import it.unicam.cs.mpgc.rpg126043.data.MonsterData;

import java.util.List;

public interface SpawnSource {
    List<MonsterData> createSpawns();
}
