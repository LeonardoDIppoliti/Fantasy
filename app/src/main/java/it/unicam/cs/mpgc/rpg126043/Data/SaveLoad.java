package it.unicam.cs.mpgc.rpg126043.Data;

import it.unicam.cs.mpgc.rpg126043.Entity.Entity;
import it.unicam.cs.mpgc.rpg126043.Entity.Player;
import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class SaveLoad {
    GamePanel gP;
    public SaveLoad(GamePanel gP) {
        this.gP=gP;
    }

    public void save() {
        File filePath= new File("save.dat");
        Player p = gP.player;
        DataStorage ds = new DataStorage();
        //PLAYER ATTRIBUTES
        ds.attack=p.getAttack();
        ds.defense=p.getDefense();
        ds.maxLife=p.getMaxLife();
        ds.currentLife=p.getCurrentLife();
        ds.exp=p.getExp();
        ds.level=p.getLevel();
        ds.nextLevelExp=p.getNextLevelExp();
        ds.worldX=p.getWorldX();
        ds.worldY=p.getWorldY();

        ds.monsters=gP.aSetter.aliveMonsters();
        try{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
        //WRITE THE DATA STORAGE
            oos.writeObject(ds);

        }catch (IOException e){
            System.out.println("SAVE EXCEPTION");
        }
    }
    public boolean load(){
        File filePath=new File("save.dat");
        if(!filePath.exists()){
            System.err.println("SAVE EXCEPTION");
            return false;
        }
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            DataStorage ds= (DataStorage) ois.readObject();
            Player p=gP.player;
            p.restoreProgress(ds.level, ds.exp,ds.nextLevelExp);
            p.restoreStats(ds.maxLife, ds.currentLife, ds.attack, ds.defense);
            p.setWorldX(ds.worldX);
            p.setWorldY(ds.worldY);


            gP.aSetter.spawnMonsters(ds.monsters);
            return true;

        }catch (IOException | ClassNotFoundException e){
            System.out.println("LOAD EXCEPTION");
            return false;
        }
    }

}
