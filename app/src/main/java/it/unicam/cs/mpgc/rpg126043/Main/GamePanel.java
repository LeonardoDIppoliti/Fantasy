package it.unicam.cs.mpgc.rpg126043.Main;

import it.unicam.cs.mpgc.rpg126043.Data.SaveLoad;
import it.unicam.cs.mpgc.rpg126043.Entity.*;
import it.unicam.cs.mpgc.rpg126043.Tile.TileManager;
import it.unicam.cs.mpgc.rpg126043.UI.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    private final int originalTileSize=16; // 16x16 tile
    private final int scale = 3;
    public final int tileSize=originalTileSize*scale; //48x48 tile size
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize*maxScreenCol; //786 pixels
    public final int screenHeight = tileSize*maxScreenRow; //576 pixels

    //WORLD PARAMETERS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // Frame Per Second
    int FPS = 60;

    //System
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public CombatHandler combatHandler = new CombatHandler(this);
    public EventHandler eHhandler = new EventHandler(this);
    public AssetSetter aSetter = new AssetSetter(this,new FixedSpawnSource(tileSize));
    public UI ui = new UI(this);
    SaveLoad saveLoad = new SaveLoad(this);
    private GameScreenHandler gScreenHandler = new GameScreenHandler(this);
    boolean gamefinished=false;


    //Entity , Player And Object
    public Entity[] npc = new Entity[10];
    public Player player = new Player(this, keyH);
    public Entity[] monster= new Entity[20];
    List<Entity> entityList = new ArrayList<>();

    //Game state
    private final TitleScreen  titleScreen = new TitleScreen(this);
    private final PlayScreen playScreen = new PlayScreen(this);
    private final MenuScreen menuScreen = new MenuScreen(this);
    private final DialogueScreen dialogueScreen = new DialogueScreen(this);
    private final ControlsScreen controlsScreen = new ControlsScreen(this);
    private final CharacterStatusScreen characterStatusScreen = new CharacterStatusScreen(this);
    private final GameOverScreen  gameOverScreen = new GameOverScreen(this);


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setUpGame(){
        aSetter.setNPC();
        aSetter.setMonster();
        ui.setGameStateIndex(UI.GameState.titleScreen.ordinal());
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
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
        if(ui.getGameStateIndex() == UI.GameState.playscreen.ordinal()){
            //PLAYER
            player.update();

            //NPC
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.update();
                }
            }

            //MONSTER
            for (int i=0; i<monster.length; i++) {

                if (monster[i] != null ) {
                    if (monster[i].isAlive()&& !monster[i].isDead()) {
                        monster[i].update();
                    }
                    if (!monster[i].isAlive()) {
                        monster[i] = null;
                    }
                }




            }
            if (allMonstersDead()) {
                gamefinished=true;
                ui.showGameOver(true);
            }
        }

    }
    public void resetGame(){
        aSetter.setNPC();
        aSetter.setMonster();
        playScreen.getMessageList().clear();
        playScreen.getMessageCounter().clear();
        player.setDefaultValues();
        gamefinished = false;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

         Graphics2D g2 = (Graphics2D) g;

        if(ui.getGameStateIndex() == UI.GameState.titleScreen.ordinal()){
            ui.draw(g2);

       }else {

            //TILE
            tileM.draw(g2);


            //ADD ENTITY TO ARRAYLIST
            entityList.add(player);
            for (Entity entity : npc) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }
            for (Entity entity : monster) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }
            //SORT
            entityList.sort(new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {

                    return Integer.compare(e1.worldY, e2.worldY);
                }
            });

            //DRAW ENTITIES
            for (Entity entity : entityList) {
                entity.draw(g2);
            }

            //EMPTY ENTITIES
            entityList.clear();


            //UI
            ui.draw(g2);
            g2.dispose();
        }
    }
    public boolean allMonstersDead(){
        for (Entity entity : monster) {
            if (entity != null) {
                return false;
            }
        }

        return true;

    }

    public TitleScreen getTitleScreen() {
        return titleScreen;
    }
    public  PlayScreen getPlayScreen() {
        return playScreen;
    }
    public  MenuScreen getMenuScreen() {
        return menuScreen;
    }
    public  DialogueScreen getDialogueScreen() {
     return dialogueScreen;
    }
    public  ControlsScreen getControlsScreen() {
        return controlsScreen;
    }
    public  GameOverScreen getGameOverScreen() {
        return gameOverScreen;
    }
    public CharacterStatusScreen getCharacterStatusScreen() {
        return characterStatusScreen;
    }
    public SaveLoad getSaveLoad() { return saveLoad; }
    public GameScreenHandler getGameScreenHandler() {return gScreenHandler;}
    public boolean gameFinished(){return gamefinished;}
}
