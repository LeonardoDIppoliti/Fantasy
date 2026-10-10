package it.unicam.cs.mpgc.rpg126043.Main;

import it.unicam.cs.mpgc.rpg126043.Entity.*;
import it.unicam.cs.mpgc.rpg126043.Tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    private final int originaltilesize=16; // 16x16 tile
    private final int scale = 3;
    public final int tilesize=originaltilesize*scale; //48x48 tile size
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tilesize*maxScreenCol; //786 pixels
    public final int screenHeight = tilesize*maxScreenRow; //576 pixels

    //WORLD PARAMETERS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tilesize*maxWorldCol;
    public final int worldHeight = tilesize*maxWorldRow;

    // Frame Per Second
    int FPS = 60;

    //System
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public EventHandler eHhandler = new EventHandler(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);

    //Entity , Player And Object
    public Entity[] npc = new Entity[10];
    public Player player = new Player(this, keyH);
    public PartyHandler pHandler = new PartyHandler(this);
    public Entity[] obj = new Entity[10];
    public Entity[] monster= new Entity[20];
    List<Entity> entityList = new ArrayList<Entity>();

    //Game state
    public int gameState;
    public final int titleState=0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionState = 5;
    //public final int battleState;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setUpGame(){
        aSetter.setNPC();
        aSetter.setObject();
        aSetter.setMonster();
        gameState = titleState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double deltaTime = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer=0;
        int drawCount=0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            deltaTime += (currentTime - lastTime) / drawInterval;
            timer+=(currentTime - lastTime);
            lastTime = currentTime;

            if(deltaTime >= 1) {
                update();
                repaint();
                deltaTime --;
                drawCount ++;
            }
            if(timer>= 1000000000) {
                drawCount = 0;
                timer = 0;
            }
        }

    }
    public void update(){
        if(gameState == playState){
            //PLAYER
            player.update();

            //NPC
            for (int i = 0; i < npc.length; i++) {
                if(npc[i] != null) {
                    npc[i].update();
                }
            }

            //MONSTER
            for (int i = 0; i < monster.length; i++) {
                if(monster[i] != null) {
                    monster[i].update();
                }
            }
        }
        if(gameState == pauseState){

        }
//        if(gameState == battleState){
//
//        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2= (Graphics2D) g;

        //TITLE
        if(gameState == titleState){
            ui.draw(g2);

        }else {

            //TILE
            tileM.draw(g2);


            //ADD ENTITY TO ARRAYLIST
            entityList.add(player);
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if(monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }
            //SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);

                    return result;
                }
            });

            //DRAW ENTITIES
            for(int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(g2);
            }

            //EMPTY ENTITIES
            entityList.clear();


            //UI
            ui.draw(g2);
            g2.dispose();
        }
    }
}
