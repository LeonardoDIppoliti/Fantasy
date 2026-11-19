package it.unicam.cs.mpgc.rpg126043.data;

import it.unicam.cs.mpgc.rpg126043.entity.Player;
import it.unicam.cs.mpgc.rpg126043.interfaces.MonsterSpawner;
import it.unicam.cs.mpgc.rpg126043.interfaces.WorldView;
import it.unicam.cs.mpgc.rpg126043.entity.monster.Monster;
import it.unicam.cs.mpgc.rpg126043.entity.monster.MonsterRegistry;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
/** Saves and loads the game to a file, passing only simple, serializable data. */
public class SaveLoad {
    private final Logger dataLogger = Logger.getLogger(SaveLoad.class.getName());
    private final Path saveFile = Path.of("save.dat");

    private final Supplier<Player> player;
    private final WorldView worldView;
    private final MonsterRegistry monsterRegistry;
    private final MonsterSpawner monsterSpawner;

    public SaveLoad(Supplier<Player> player, WorldView worldView,
                    MonsterRegistry monsterRegistry, MonsterSpawner monsterSpawner) {
        this.player = player;
        this.worldView = worldView;
        this.monsterRegistry = monsterRegistry;
        this.monsterSpawner = monsterSpawner;
    }
    /*
    * Save only the data needed to rebuild the game, not the game objects:
    * they contain images and references to Swing, which cannot be serialized.
    * Dying monsters are excluded,
    * as they would appear alive upon loading.
    * */
    public void save() {
        DataStorage ds = new DataStorage(playerStatus(),monsterStatus());
        try{
            ObjectOutputStream OBT = new ObjectOutputStream(Files.newOutputStream(saveFile));
            OBT.writeObject(ds);
        } catch (IOException e) {
            dataLogger.log(Level.WARNING,"SAVE FAILED",e);
        }
    }

    /*
    * Returns false instead of an exception if the file is missing or
    * corrupted; the title remains active and the game does not crash
    * */
    public boolean load(){

    if(!Files.exists(saveFile)){
            dataLogger.warning("SAVE FILED NOT FOUND");
            return false;
        }
        try{
            ObjectInputStream OIS = new ObjectInputStream(Files.newInputStream(saveFile));
            DataStorage ds= (DataStorage) OIS.readObject();
            restorePlayer(ds.getPlayerData());
            monsterSpawner.spawnMonsters(ds.getMonsters());
            return true;

        }catch (IOException | ClassNotFoundException e){
            dataLogger.log(Level.WARNING,"SAVE FILE NOT VALID",e);
            return false;
        }
    }

    private PlayerData playerStatus(){
        Player p = player.get();
        return new PlayerData(p.getLevel(), p.getMaxLife(), p.getCurrentLife(),
                p.getDefense(), p.getAttack(), p.getExp(), p.getNextLevelExp(),
                p.getWorldX(), p.getWorldY());
    }

    private List<MonsterData> monsterStatus(){
        List<MonsterData> monsterData = new ArrayList<>();
        for (Monster mon : worldView.getMonster()){
            if(mon==null||!mon.isAlive()|| mon.isDying()){continue;}
            monsterRegistry.typeOfMonster(mon).ifPresentOrElse(
                    type->monsterData.add(new MonsterData(type, mon.getWorldX(), mon.getWorldY(), mon.getCurrentLife())),
            ()-> dataLogger.warning("MONSTER NOT REGISTERED"+mon.getClass().getSimpleName())
            );
        }
        return monsterData;
    }

    private void restorePlayer(PlayerData playerData){
        Player p = player.get();
        p.restoreProgress(playerData.level(),playerData.exp(),playerData.nextLevelExp());
        p.restoreStats(playerData.maxLife(), playerData.currentLife(), playerData.attack(), playerData.defense());
        p.setWorldX(playerData.worldX());
        p.setWorldY(playerData.worldY());
    }

}
