package it.unicam.cs.mpgc.rpg126043.Monster;

import it.unicam.cs.mpgc.rpg126043.Entity.Character;
import it.unicam.cs.mpgc.rpg126043.Entity.Stats;
import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.awt.*;

public abstract class Monster extends Character {
    private final int dyingBlinkStep=5;
    private final int totalDyingFrames=dyingBlinkStep*8;
    private static final int healthBarFrames=600;
    private static final int invincibleFrames=40;

    private final int rewardExp;
    private boolean alive=true ,dying, hpBarOn;
    private int hpBarCounter,dyingCounter;

    public Monster(GamePanel gP, String name, int speed, Stats stats, int rewardExp) {
        super(gP, name, speed, stats, invincibleFrames);
        this.rewardExp = rewardExp;
    }

    @Override
    public void update() {
        if(dying){updateDying(); return;}
        super.update();
        if(hpBarOn && ++hpBarCounter>healthBarFrames) {
           hpBarOn=false;
        }
    }
    private void updateDying(){
        if(++dyingCounter>totalDyingFrames) {
            alive=false;
            dying=false;
        }
    }
    @Override
    protected void onPlayerContact(){
        gP.combatHandler.contactMonster(this);
    }

    @Override
    public boolean takeDamage(int damage){
        boolean hit=super.takeDamage(damage);
        if(hit){
            hpBarOn=true;
            hpBarCounter=0;
        }
        return hit;
    }
    public void damageReaction(){}

    public void startDying(){
        dying=true;
    }

    public int getRewardExp() {
        return rewardExp;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isDying() {
        return dying;
    }
    @Override
    protected void drawBody(Graphics2D g2,int screenX, int screenY) {
        if(hpBarOn){drawHpBar(g2,screenX,screenY);}
        if(dying){
            boolean hidden = ((dyingCounter-1)/dyingBlinkStep)%2==0;
            setAlpha(g2, hidden ? 0f : 1f);
        } else if (isInvincible()) {
            setAlpha(g2, 0.4f);
        }
        super.drawBody(g2,screenX, screenY);
        setAlpha(g2, 1f);
    }
    private void drawHpBar(Graphics2D g2, int screenX, int screenY) {
        int width = (int) ((double) gP.getTileSize()*getCurrentLife()/getMaxLife());
        g2.setColor(new Color(35, 35, 35));
        g2.fillRect(screenX-1,screenY-16,gP.getTileSize()+2,12);
        g2.setColor(new Color(250, 0, 30));
        g2.fillRect(screenX,screenY-15,width,10);
    }
}
