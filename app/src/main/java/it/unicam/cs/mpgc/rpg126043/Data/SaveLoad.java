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
        ds.attack=p.attack;
        ds.defense=p.defense;
        ds.maxLife=p.maxLife;
        ds.currentLife=p.currentLife;
        ds.exp=p.getExp();
        ds.level=p.getLevel();
        ds.nextLevelExp=p.getNextLevelExp();
        ds.worldX=p.worldX;
        ds.worldY=p.worldY;
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
            p.setExp(ds.exp);
            p.setCurrentLife(ds.currentLife);
            p.setNextLevelExp(ds.nextLevelExp);
            p.setLevel( ds.level);
            p.setDefense(ds.defense);
            p.setMaxLife(ds.maxLife);
            p.setStrength(ds.attack);
            p.setInvincible(false);
            p.worldX=ds.worldX;
            p.worldY=ds.worldY;

            gP.aSetter.spawnMonsters(ds.monsters);
            return true;

        }catch (IOException | ClassNotFoundException e){
            System.out.println("LOAD EXCEPTION");
            return false;
        }
    }

}
