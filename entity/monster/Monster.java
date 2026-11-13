package it.unicam.cs.mpgc.rpg126043.entity.monster;

import it.unicam.cs.mpgc.rpg126043.entity.GameCharacter;
import it.unicam.cs.mpgc.rpg126043.entity.Direction;
import it.unicam.cs.mpgc.rpg126043.entity.Stats;
import it.unicam.cs.mpgc.rpg126043.interfaces.CombatService;
import it.unicam.cs.mpgc.rpg126043.interfaces.EntityContext;
import it.unicam.cs.mpgc.rpg126043.ui.component.HealthBar;

import java.awt.*;
/*
* Enemies base class: death with animation, health bar and experience given to player
* */
public abstract class Monster extends GameCharacter {
    private static final int DYING_BLINK_STEP = 5;
    private static final int TOTAL_DYING_FRAMES = DYING_BLINK_STEP * 8;
    private static final int HEALTH_BAR_FRAMES = 600, HEALTH_BAR_OFFSET = 15, HEALTH_BAR_HEIGHT = 10;
    private static final int INVINCIBLE_FRAMES = 40;
    private static final float ALPHA=0.4F,ALPHA_RESET=1F;

    private final CombatService combatService;
    private final int rewardExp;
    private boolean alive=true ,dying, hpBarOn;
    private int hpBarCounter,dyingCounter;

    public Monster(EntityContext entityContext,CombatService combatService, String name, int speed, Stats stats, int rewardExp) {
        super(entityContext, name, speed, stats, INVINCIBLE_FRAMES);
        this.combatService=combatService;
        this.rewardExp = rewardExp;
    }
    //LOGIC SECTION
    @Override
    public void update() {
        if(dying){updateDying(); return;}
        super.update();
        if(hpBarOn && ++hpBarCounter> HEALTH_BAR_FRAMES) {
           hpBarOn=false;
        }
    }
    private void updateDying(){
        if(++dyingCounter> TOTAL_DYING_FRAMES) {
            alive=false;
            dying=false;
        }
    }
    @Override
    protected void onPlayerContact(){
        combatService.monsterTouchedPlayer(this);
    }

    //COMBAT SECTION

    @Override
    public boolean takeDamage(int damage){
        boolean hit=super.takeDamage(damage);
        if(hit){
            hpBarOn=true;
            hpBarCounter=0;
        }
        return hit;
    }
    /*
    * Specific reaction to the hit received.
    * it receives the direction as a parameter so
    * Monster doesn't need to know Player only the direction it was hit
    * */
    public void damageReaction(Direction hitDirection){}
    /*
    * Monster doesn't disappear immediately:
    * it remains in "dying" state for some frames (flashing)
    * only after this isAlive is set to false and gets removed from the array by GameWorld.
    * During the animation it does not move nor produce damage
    * */
    public void startDying(){
        dying=true;
    }

    //DRAW SECTION

    @Override
    protected void drawBody(Graphics2D g2,int screenX, int screenY) {
        if(hpBarOn){drawHpBar(g2,screenX,screenY);}
        if(dying){
            boolean hidden = ((dyingCounter-1)/ DYING_BLINK_STEP)%2==0;
            setAlpha(g2, hidden ? 0F : 1F);
        } else if (isInvincible()) {
            setAlpha(g2, ALPHA);
        }
        super.drawBody(g2,screenX, screenY);
        setAlpha(g2, ALPHA_RESET);
    }
    private void drawHpBar(Graphics2D g2, int screenX, int screenY) {
        HealthBar.drawBar(g2,screenX,screenY - HEALTH_BAR_OFFSET,
                entityContext.getTileSize(), HEALTH_BAR_HEIGHT,getCurrentLife(),getMaxLife());
    }


    //ACCESS GETTER/SETTER SECTION

    public int getRewardExp() {
        return rewardExp;
    }
    public boolean isAlive() {
        return alive;
    }
    public boolean isDying() {
        return dying;
    }
}
