package it.unicam.cs.mpgc.rpg126043.Data;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.io.*;

public class SaveLoad {
    GamePanel gP;
    public SaveLoad(GamePanel gP) {
        this.gP=gP;
    }

    public void save() {
        try{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.dat"));
        DataStorage ds = new DataStorage();
        ds.attack=gP.player.attack;
        ds.defense=gP.player.defense;
        ds.maxLife=gP.player.maxLife;
        ds.currentLife=gP.player.currentLife;
        ds.exp=gP.player.getExp();
        ds.level=gP.player.getLevel();
        ds.nextLevelExp=gP.player.getNextLevelExp();

        //WRITE THE DATA STORAGE
            oos.writeObject(ds);


        }catch (IOException e){
            System.out.println("SAVE EXCEPTION");
        }
    }
    public void load(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.dat"));
            DataStorage ds= (DataStorage) ois.readObject();
            gP.player.setExp(ds.exp);
            gP.player.setCurrentLife(ds.currentLife);
            gP.player.setNextLevelExp(ds.nextLevelExp);
            gP.player.setLevel( ds.level);
            gP.player.setDefense(ds.defense);
            gP.player.setMaxLife(ds.maxLife);
            gP.player.setStrength(ds.attack);


        }catch (IOException e)
        {   System.out.println("LOAD EXCEPTION");} catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
