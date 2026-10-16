package it.unicam.cs.mpgc.rpg126043.Main;

import it.unicam.cs.mpgc.rpg126043.Data.MonsterData;
import it.unicam.cs.mpgc.rpg126043.Entity.Entity;
import it.unicam.cs.mpgc.rpg126043.Entity.NPC_OldMan;
import it.unicam.cs.mpgc.rpg126043.Interface.SpawnSource;
import it.unicam.cs.mpgc.rpg126043.Monster.MON_GreenSlime;

import java.util.*;
import java.util.function.Function;

public class AssetSetter {
    GamePanel gP;
    private final SpawnSource spawnSource;
    private Map<String, Function<GamePanel, Entity>> factories = new HashMap<>();
    private Map<Class<? extends Entity>, String> names = new HashMap<>();

    public AssetSetter(GamePanel gP, SpawnSource spawnSource) {
        this.gP = gP;
        this.spawnSource = spawnSource;
        register("slime", MON_GreenSlime.class, MON_GreenSlime::new);
    }

    private void register(String name, Class<? extends Entity> monClass, Function<GamePanel, Entity> factory) {
        factories.put(name, factory);
        names.put(monClass, name);
    }



    public void setNPC() {
        Arrays.fill(gP.npc, null);
        int i = 0;
        gP.npc[i] = new NPC_OldMan(gP);
        gP.npc[i].worldX = gP.tileSize * 21;
        gP.npc[i].worldY = gP.tileSize * 21;
    }

    public void setMonster() {
        spawnMonsters(spawnSource.createSpawns());
    }

    public void spawnMonsters(List<MonsterData> monsters) {
        Arrays.fill(gP.monster, null);
        int index = 0;
        for(MonsterData data : monsters){
            Function<GamePanel,Entity> factory = factories.get(data.type());
            if (factory == null) {
                System.err.println("Error: Monster " + data.type() + " not found!");
                continue;
            }
            if(index >= gP.monster.length){
                System.err.println("ARRAY MOSTRI PIENO");
                break;
            }
            Entity mon=factory.apply(gP);
            mon.worldX=data.worldX();
            mon.worldY=data.worldY();
            if(data.currentLife()>0){
            mon.currentLife=data.currentLife();}
            gP.monster[index++]=mon;

        }
    }
    public List<MonsterData> aliveMonsters() {
        List<MonsterData> monsterAlive = new ArrayList<>();
        for(Entity mon : gP.monster){
            if(mon == null ||!mon.isAlive() || mon.isDead()){ continue;}
            String type = names.get(mon.getClass());
            if(type == null) {
                System.err.println("Monster non registrato " + mon.getClass().getSimpleName());
                continue;
            }
            monsterAlive.add(new MonsterData(type,mon.worldX,mon.worldY,mon.currentLife));
        }
        return monsterAlive;
    }
}
