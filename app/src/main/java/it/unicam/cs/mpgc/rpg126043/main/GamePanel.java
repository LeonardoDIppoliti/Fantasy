package it.unicam.cs.mpgc.rpg126043.main;

import it.unicam.cs.mpgc.rpg126043.data.SaveLoad;
import it.unicam.cs.mpgc.rpg126043.entity.*;
import it.unicam.cs.mpgc.rpg126043.events.EventHandler;
import it.unicam.cs.mpgc.rpg126043.interfaces.Camera;
import it.unicam.cs.mpgc.rpg126043.interfaces.MovementCollisionService;
import it.unicam.cs.mpgc.rpg126043.interfaces.EntityContext;
import it.unicam.cs.mpgc.rpg126043.interfaces.UIContext;
import it.unicam.cs.mpgc.rpg126043.entity.monster.MonsterRegistry;
import it.unicam.cs.mpgc.rpg126043.tile.TileManager;
import it.unicam.cs.mpgc.rpg126043.ui.*;
import it.unicam.cs.mpgc.rpg126043.ui.component.CharacterStatusScreen;
import it.unicam.cs.mpgc.rpg126043.ui.component.MessageLog;
import it.unicam.cs.mpgc.rpg126043.ui.screen.TitleScreen;
import it.unicam.cs.mpgc.rpg126043.ui.screen.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/** Game Panel: loop with Timer, drawing and assembling all components. */
public class GamePanel extends JPanel implements EntityContext, UIContext {
    //SCREEN SETTINGS
    private static final int ORIGINAL_TILE_SIZE =16; // 16x16 tile
    private static final int SCALE = 3;
    private final int tileSize= ORIGINAL_TILE_SIZE * SCALE; //48x48 tile size
    private static final int MAX_SCREEN_COL = 16;
    private static final int MAX_SCREEN_ROW = 12;
    private final int screenWidth = tileSize* MAX_SCREEN_COL; //786 pixels
    private final int screenHeight = tileSize* MAX_SCREEN_ROW; //576 pixels

    //WORLD PARAMETERS
    private static final int MAX_WORLD_COL = 50;
    private static final int MAX_WORLD_ROW = 50;

    // Frame Per Second
    private static final int FPS = 60;

    //System
    private final KeyHandler keyH = new KeyHandler();
    private final Timer gameTimer = new Timer(1000 / FPS , e -> tick());
    private final ScreenNavigator navi = new ScreenNavigator(keyH);
    private final MessageLog messageLog = new MessageLog();
    private DialogueScreen dialogueScreen;
    private GameOverScreen gameOverScreen;
    private final MonsterRegistry monsterRegistry=new MonsterRegistry();
    private final TileManager tileManager = new TileManager(tileSize, MAX_WORLD_COL, MAX_WORLD_ROW);

    /*
    * The Suppliers in the system builder defer the reading of gameWorld:
    * the world was created after them but needs them.
    * The lambda is used, not the method reference,
    * which would evaluate gameWorld (still null) immediately.
    * */
    private final CollisionChecker collisionChecker =new CollisionChecker(tileManager,tileSize,()->this.gameWorld);
    private final EventHandler eventHandler =new EventHandler(tileSize,() ->this.gameWorld.getPlayer());
    private final CombatHandler combatHandler = new CombatHandler(() ->this.gameWorld.getPlayer());

    private final GameWorld gameWorld=new GameWorld(this,
            combatHandler, eventHandler,keyH, collisionChecker,screenWidth,screenHeight);
    private final Camera camera =
            new PlayerCamera(gameWorld.getPlayer(),screenWidth,screenHeight);



    public GamePanel(){
        setPreferredSize(new Dimension(screenWidth,screenHeight));
        setBackground(Color.black);
        setDoubleBuffered(true);
        addKeyListener(keyH);
        setFocusable(true);
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent fEvent){
                keyH.releaseAll();
            }
        });
   }

   //LOGIC SECTION
    public void setUpGame(){
        dialogueScreen=new DialogueScreen(this,navi);
        combatHandler.setCombatListener(new CombatFeedback(messageLog,dialogueScreen));

        AssetSetter aSetter =new AssetSetter(gameWorld,this,combatHandler,
                dialogueScreen, new FixedSpawnSource(tileSize),monsterRegistry);

        SaveLoad saveLoad = new SaveLoad(gameWorld::getPlayer,gameWorld,
                monsterRegistry,aSetter);

        GameFlow gameFlow = new GameFlow(this::getPlayer,navi,aSetter,saveLoad,messageLog);
        gameOverScreen=new GameOverScreen(this,navi,gameFlow);

        navi.registerScreen(ScreenId.TITLE,new TitleScreen(this,navi,gameFlow));
        navi.registerScreen(ScreenId.MENU,new MenuScreen(this,navi,gameFlow));
        registerScreens(gameFlow);
        registerEvents(saveLoad);


        gameFlow.populateWorld();
        navi.goToScreen(ScreenId.TITLE);
    }
    private void registerScreens(GameFlow gameFlow){
        navi.registerScreen(ScreenId.PLAY,new PlayScreen(this,navi,messageLog));
        navi.registerScreen(ScreenId.CONTROLS,new ControlsScreen(this,navi));
        navi.registerScreen(ScreenId.STATUS,new CharacterStatusScreen(this,navi));
        navi.registerScreen(ScreenId.DIALOGUE,dialogueScreen);
        navi.registerScreen(ScreenId.GAME_OVER,gameOverScreen);
    }
    private void registerEvents(SaveLoad saveLoad){
        //healing Pool
        eventHandler.registerEvent(23,7,Direction.UP,true,()-> {
            gameWorld.getPlayer().restoreLife();
            saveLoad.save();
            dialogueScreen.show("You Drink the water \n Your wounds recovered \n (Progress has been saved)");
        });
    }

    public void startGame(){
        gameTimer.start();
    }
    /*
    *Game loop with a javax.swing.Timer:
    * updating, input, and drawing all run on the graphics thread,
    * so no synchronization is needed on shared data.
    * */
    private void tick(){
        update();
        repaint();
    }

    private void update(){
        navi.update();
        if(navi.isPlaying()){return;}
       gameWorld.updateWorld();
        if(navi.isPlaying()){return;} // An Event could start a dialogue
        if(gameWorld.getPlayer().isDefeated()){
            gameOverScreen.show(false);
        } else if (gameWorld.allMonsterDead()) {
            gameOverScreen.show(true);
        }

    }

    //paintComponent OVERRIDE
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(navi.getId() != ScreenId.TITLE){
            gameWorld.drawWorld(g2, tileManager,camera);
        }
        g2.setFont(UITheme.BASE_FONT);
        g2.setColor(UITheme.COLOR_TEXT);
        navi.draw(g2);
    }

    //EntityContext OVERRIDE
    @Override
    public int getTileSize() {
        return tileSize;
    }

    @Override
    public Camera getCamera() {
        return camera;
    }

    @Override
    public MovementCollisionService getCollision() {
        return collisionChecker;
    }


    //ACCESS GETTER/SETTER SECTION
    @Override
    public int getScreenWidth() {
        return screenWidth;
    }
    @Override
    public int getScreenHeight() {
        return screenHeight;
    }
    @Override
    public Player getPlayer(){return gameWorld.getPlayer();}

}
