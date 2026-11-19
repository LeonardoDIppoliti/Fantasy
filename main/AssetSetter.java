package it.unicam.cs.mpgc.rpg126043.main;

import it.unicam.cs.mpgc.rpg126043.data.MonsterData;
import it.unicam.cs.mpgc.rpg126043.entity.NPC;
import it.unicam.cs.mpgc.rpg126043.entity.OldManNPC;
import it.unicam.cs.mpgc.rpg126043.interfaces.*;
import it.unicam.cs.mpgc.rpg126043.entity.monster.Monster;
import it.unicam.cs.mpgc.rpg126043.entity.monster.MonsterRegistry;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/** Positions NPCs and monsters in the world, both at startup and after loading. */
public class AssetSetter implements MonsterSpawner {
    private final Logger assetSetterLogger = Logger.getLogger(AssetSetter.class.getName());
    private static final int OLD_MAN_COL = 21, OLD_MAN_ROW = 21;

    private final WorldView worldView;
    private final EntityContext entityContext;
    private final CombatService combatService;
    private final DialogueService dialogueService;
    private final SpawnSource spawnSource;
    private final MonsterRegistry registry;

    public AssetSetter(WorldView worldView, EntityContext entityContext, CombatService combatService,
            DialogueService dialogueService, SpawnSource spawnSource, MonsterRegistry registry) {
        this.worldView = worldView;
        this.entityContext = entityContext;
        this.combatService = combatService;
        this.dialogueService = dialogueService;
        this.spawnSource = spawnSource;
        this.registry = registry;
    }

    public void setNPC() {
        Arrays.fill(worldView.getNpc(), null);
        NPC oldMan = new OldManNPC(entityContext,dialogueService);
        oldMan.setWorldX(entityContext.getTileSize() * OLD_MAN_COL);
        oldMan.setWorldY(entityContext.getTileSize() * OLD_MAN_ROW);
        worldView.getNpc()[0] = oldMan;
    }

    public void setMonster() {
        spawnMonsters(spawnSource.createSpawns());
    }

    @Override
    /*
    * Recreates monsters from a data list, for initial spawn and loading.
    * Always clear the array first, otherwise the monsters will pile up after a reset.
    * currentLife = 0 is a conventional value for "full builder health":
    * a saved monster always has health > 0.
    * */
    public void spawnMonsters(List<MonsterData> monsterData) {
        Monster[] slots = worldView.getMonster();
        Arrays.fill(slots, null);
        int index = 0;
        for(MonsterData data : monsterData){
            if(index >= slots.length){
                assetSetterLogger.warning("MONSTER ARRAY FULL");
                break;
            }
            Optional<Monster> monsterCreated = registry.createMonster(data.type(),entityContext,combatService);
            if(monsterCreated.isEmpty()){
                assetSetterLogger.log(Level.WARNING,"MONSTER TYPE UNKNOWN " + data.type());
                continue;
            }

            Monster mon = monsterCreated.get();
            mon.setWorldX(data.worldX());
            mon.setWorldY(data.worldY());
            if(data.currentLife()>0){
            mon.setCurrentLife(data.currentLife());}
            slots[index++]=mon;

        }
    }
}
