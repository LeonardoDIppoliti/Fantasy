package it.unicam.cs.mpgc.rpg126043.entity;


import it.unicam.cs.mpgc.rpg126043.interfaces.*;
import java.awt.*;
/*
* Character controlled by the player: input,attack,interactions and progression
* */
public class Player extends GameCharacter {
    //CONSTANTS
    private static final int ATTACK_SIZE = 36, INVINCIBLE_FRAMES = 60,START_X =23, START_Y =21;
    private static final int SPRITE_1 =1;
    private static final int SPEED=4;
    private static final Stats STATS = new Stats(12,10,10); //maxLife,attack,defense
    private static final int LVL_UP_LIFE=12,LVL_UP_ATTACK=10,LVL_UP_DEFENSE=10;
    private static final int AREA_X=8,AREA_Y=16,AREA_WIDTH=32,AREA_HEIGHT=32;
    private static final float ALPHA=0.3F,ALPHA_RESET=1F;

    //ATTRIBUTES
    private final WorldView worldView;
    private final CombatService combatService;
    private final EventService eventService;
    private final AttackAreaService attAreaService;
    private final PlayerInput input;
    private final int screenX,screenY;
    private final SpriteSet attackSprites;
    private final Progression progress = new Progression();
    private final MeleeAttack attacking = new MeleeAttack();



    public Player(EntityContext entityContext,WorldView worldView,CombatService combatService,
                  EventService eventService,PlayerInput input,AttackAreaService attAreaService ,
                  int screenWidth,int screenHeight) {
        super(entityContext, "Player", SPEED, STATS, INVINCIBLE_FRAMES);
        this.input=input;
        this.worldView=worldView;
        this.combatService=combatService;
        this.attAreaService=attAreaService;
        this.eventService=eventService;
        screenX =  screenWidth/2-entityContext.getTileSize()/2;
        screenY = screenHeight/2-entityContext.getTileSize()/2;

        setSolidArea(AREA_X,AREA_Y,AREA_WIDTH,AREA_HEIGHT);
        sprites = loadDirectional("/PlayerImages/Walking_sprites/boy_");
        attackSprites=loadAttackSprites();
        setDefaultValues();
    }
    private SpriteSet loadAttackSprites(){
        SpriteSet setAttSprites=new SpriteSet();
        int tileSize = entityContext.getTileSize();
        int doubleTileSize = entityContext.getTileSize()*2;
        for(Direction dir:Direction.values()){
            boolean vertical = dir==Direction.UP|| dir==Direction.DOWN;
            int width = vertical ? tileSize : doubleTileSize;
            int height = vertical ? doubleTileSize : tileSize;
            String playerAttImage = "/PlayerImages/Attacking_sprites/boy_attack_"+dir.key();
            setAttSprites.set(dir,loadImage(playerAttImage+"_1",width,height),
                                  loadImage(playerAttImage+"_2",width,height));
        }
        return setAttSprites;
    }

    //RESET SECTION
    public void setDefaultValues(){
        setDefaultPosition();
        progress.reset();
        attacking.cancelActive();
        resetStats();
    }
    public void setDefaultPosition(){
        setWorldX(entityContext.getTileSize()* START_X);
        setWorldY(entityContext.getTileSize()* START_Y);
        direction=Direction.DOWN;
    }
    public void restoreStatus(){
        restoreLife();
        attacking.cancelActive();
    }
    public void restoreLife(){
        setCurrentLife(getMaxLife());
    }

    //INPUT & MOVEMENT SECTION

    /*
    * Doesn't call super.update(). Player movement isn't dictated by setAction()
    * like NPC and Monster, since it's input based
    * */
    @Override
    public void update() {
        if(attacking.isActive()) updateAttack();
        else if (hasInput()) {
            handleInput();
        }
            tickInvincibility();
    }
    private boolean hasInput() {
        return input.isUpPressed()||input.isDownPressed()||input.isLeftPressed()||
                input.isRightPressed()||input.hasAction();
    }

    /*
    * A single ENTER pressed can have different meanings
    * By priority : talk with NPC, use events (healingPool) or attacks
    *the event is red ONE single time with consumeAction():
    * a second reading in the same frame will obtain false
    * */
    private void handleInput() {
        turnTowardsInput();
        boolean action = input.consumeAction();
        MovementCollisionService movCollService = entityContext.getCollision();

        setCollisionOn(false);
        movCollService.checkTile(this);
        int npcIndex= movCollService.checkNpc(this);
        int monIndex=movCollService.checkMonster(this);
        if(MovementCollisionService.isHit(monIndex)){
           combatService.monsterTouchedPlayer(worldView.getMonster()[monIndex]);
        }
        boolean used=action && talkTo(npcIndex);
        used |= eventService.checkEvent(action && !used);

        if(!isCollisionOn() && !action){move();}
        if(action && !used){attacking.startActive();}

        animate();

    }
    private void turnTowardsInput(){

        if(input.isLeftPressed()) {direction=Direction.LEFT;}
        else if(input.isRightPressed()) {direction=Direction.RIGHT;}
        else if(input.isUpPressed()) {direction=Direction.UP;}
        else if(input.isDownPressed()) {direction=Direction.DOWN;}

    }

    private boolean talkTo(int index){
        if(!MovementCollisionService.isHit(index)) {return false;}
        worldView.getNpc()[index].speak(direction);
        return true;
    }

    //ATTACK SECTION
    public void updateAttack(){
       attacking.tick();
       setSpriteNum(attacking.isActive() ? attacking.spriteNum() : SPRITE_1);
       if(!attacking.isHitting()){ return;}
       int hit = attAreaService.checkArea(attackBox(),worldView.getMonster());
       if(MovementCollisionService.isHit(hit)){
           combatService.playerHitMonster(worldView.getMonster()[hit]);
       }
    }
    /*
    * Square positioned in front of player character based where its looking
    * */
    private Rectangle attackBox(){
        Rectangle base = getBounds();
        return new Rectangle(
                 ( base.x + direction.getDx()* ATTACK_SIZE),
                 ( base.y+direction.getDy()* ATTACK_SIZE),
                ATTACK_SIZE, ATTACK_SIZE
        );
    }

    //PROGRESS SECTION
    /*
    * Assign experience obtained and applies bonuses if level goes up
    * returning true, this way who calls the method can show a messaged
    * without Player knowing the UI
    * */
    public boolean checkLevelUp(int rewardExp){
        boolean levelUp = progress.gainExp(rewardExp);
        if(levelUp) increaseStats(LVL_UP_LIFE,LVL_UP_ATTACK,LVL_UP_DEFENSE);
        return levelUp;
    }
    public void restoreProgress(int level, int exp,int nextLevelExp){
        progress.restore(level,exp,nextLevelExp);
    }

    //DRAW SECTION
    @Override
    protected void drawBody(Graphics2D g2, int screenX, int screenY) {
        if (isInvincible()) setAlpha(g2, ALPHA);
        if (attacking.isActive()) {
            int x = screenX, y = screenY;
            if (direction == Direction.UP) {
                y -= entityContext.getTileSize();
            }
            if (direction == Direction.LEFT) {
                x -= entityContext.getTileSize();
            }
            g2.drawImage(attackSprites.get(direction, spriteNum), x, y, null);
        } else {
            super.drawBody(g2, screenX, screenY);
        }
        setAlpha(g2, ALPHA_RESET);
    }

    //ACCESS GETTER SECTION
    public int getNextLevelExp() {
        return progress.getNextLevelExp();
    }
    public int getLevel() {
        return progress.getLevel();
    }
    public int getExp() {
        return progress.getExp();
    }
    public int getScreenX() {return screenX;}
    public int getScreenY() {return screenY;}
    public SpriteSet getSprites() {return sprites;}

}

