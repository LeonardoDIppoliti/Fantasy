package it.unicam.cs.mpgc.rpg126043.Entity;


import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;
import it.unicam.cs.mpgc.rpg126043.Main.KeyHandler;
import it.unicam.cs.mpgc.rpg126043.UI.UI;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Character {
    private final int indexMax=999;
    private final int attackSize=36,attackWindUp=5,attackEnd=25;
    private static final int startX =23, startY=21;
    private final int sprite1=1, sprite2=2;

    private final KeyHandler keyH;
    private final int screenX =  gP.getScreenWidth()/2-gP.getTileSize()/2,
    screenY = gP.getScreenHeight()/2-gP.getTileSize()/2;;
    private final SpriteSet attackSprites;

    private int level,exp,nextLevelExp;
    private boolean attacking,attackCancelled;
    private int attackFrame;

    public Player(GamePanel gP,KeyHandler keyH) {
        super(gP, "Player", 4, new Stats(12,10,10), 60);
        this.keyH=keyH;

        setSolidArea(8,16,32,32);
        sprites = loadDirectional("/PlayerImages/Walking_sprites/boy_");
        attackSprites=loadAttackSprites();
        setDefaultValues();
    }
    private SpriteSet loadAttackSprites(){
        SpriteSet setAttSprites=new SpriteSet();
        int tileSize = gP.getTileSize();
        int doubleTileSize = gP.getTileSize()*2;
        for(Direction dir:Direction.values()){
            boolean vertical = dir==Direction.UP|| dir==Direction.DOWN;
            int width = vertical ? tileSize : doubleTileSize;
            int height = vertical ? doubleTileSize : tileSize;
            String playerAttImage="/PlayerImages/Attacking_sprites/boy_attack_"+dir.key();
        setAttSprites.set(dir,loadImage(playerAttImage+"_1",width,height),
                loadImage(playerAttImage+"_2",width,height));
        }
        return setAttSprites;
    }

    public void setDefaultValues(){
        setDefaultPosition();
        restoreProgress(1,0,5);
        attacking=false;
        attackFrame=0;
        resetStats();
    }
    public void setDefaultPosition(){
        setWorldX(gP.getTileSize()*startX);
        setWorldY(gP.getTileSize()*startY);
        direction=Direction.DOWN;
    }
    public void restoreStatus(){
        restoreLife();
        attacking=false;
    }
    public void restoreLife(){
        setCurrentLife(getMaxLife());
    }

    @Override
    public void update() {
        if(attacking) updateAttack();
        else if (hasInput()) {
            handleInput();

            tickInvincibility();
            if(isDefeated()) gP.ui.showGameOver(false);
        }

    }
    private boolean hasInput() {
        return keyH.isUpPressed()||keyH.isDownPressed()||keyH.isLeftPressed()||
                keyH.isRightPressed()||keyH.isEnterPressed();
    }

    private void handleInput() {
        if(keyH.isLeftPressed()) {direction=Direction.LEFT;}
        else if(keyH.isRightPressed()) {direction=Direction.RIGHT;}
        else if(keyH.isUpPressed()) {direction=Direction.UP;}
        else if(keyH.isDownPressed()) {direction=Direction.DOWN;}

        setCollisionOn(false);
        gP.cChecker.checkTile(this);
        interactNPC(gP.cChecker.checkEntity(this,gP.npc));
        int monIndex=gP.cChecker.checkEntity(this,gP.monster);
        if(monIndex != indexMax){
            gP.combatHandler.contactMonster(gP.monster[monIndex]);
        }
        gP.eHhandler.checkEvent();

        boolean pressedEnter= keyH.isEnterPressed();
        if(!isCollisionOn() && !pressedEnter){
            move();
        }
        if(pressedEnter && !attackCancelled){
            attacking=true;
            attackFrame=0;
        }
        attackCancelled=false;
        keyH.setEnterPressed(false);
        animate();

    }
    private void interactNPC(int index){
        if(keyH.isEnterPressed() && index!=indexMax){
            attackCancelled=true;
            gP.ui.setGameStateIndex(UI.GameState.dialogueScreen.ordinal());
            gP.npc[index].speak();
        }
    }

    public void updateAttack(){
        attackFrame++;
        if(attackFrame<= attackWindUp){
            setSpriteNum(sprite1);
        } else if (attackFrame<=attackEnd) {
            setSpriteNum(sprite2);
            int hit = gP.cChecker.checkArea(attackBox(),gP.monster);
            if(hit != indexMax){
                gP.combatHandler.damageMonster(hit);
            }
        }else {
            //RESET
            setSpriteNum(sprite1);
            attackFrame=0;
            attacking=false;
        }
    }

    private Rectangle attackBox(){
        return new Rectangle(
                (int) ( getWorldX()+getSolidArea().getX()+direction.getdX()*attackSize),
                (int) ( getWorldY()+getSolidArea().getY()+direction.getdY()*attackSize),
                attackSize,attackSize
        );
    }

    public boolean checkLevelUp(int rewardExp){
        exp+=rewardExp;
        if(exp< nextLevelExp){return false;}
        level++;
        nextLevelExp*=4;
        increaseStats(2,1,1);
        return true;
    }
    public void restoreProgress(int level, int exp,int nextlevelExp){
        this.nextLevelExp=nextlevelExp;
        this.level=level;
        this.exp=exp;
    }
    public void cancelAttack(){
        attackCancelled=true;
    }
    @Override
    protected void drawBody(Graphics2D g2,int screenX, int screenY) {
        if(isInvincible())  setAlpha(g2,0.3F);
        if(attacking){
            int x=screenX,y=screenY;
            if(direction==Direction.UP){
                y-=gP.getTileSize();
            }
            if(direction==Direction.LEFT){
                x-=gP.getTileSize();
            }
            g2.drawImage(attackSprites.get(direction,spriteNum),x,y,null);
        }else{
            super.drawBody(g2,screenX,screenY);
        }
        setAlpha(g2,1F);
    }

    public int getNextLevelExp() {
        return nextLevelExp;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public int getIndexMax() {
        return indexMax;
    }
    public SpriteSet getSprites() {
        return sprites;
    }

}

