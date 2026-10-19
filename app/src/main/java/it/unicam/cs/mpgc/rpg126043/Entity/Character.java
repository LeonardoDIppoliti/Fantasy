package it.unicam.cs.mpgc.rpg126043.Entity;

import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

import java.awt.image.BufferedImage;

public abstract class Character extends Entity {
    //CHARACTERS ATTRIBUTES
    private final Stats defaultStats;
    private final int invincibleFrames;
    private int maxLife, currentLife, defense, attack;
    private boolean invincible;
    private int invincibleCounter;



    public Character(GamePanel gP,String name,int speed,Stats stats,int invincibleFrames) {
        super(gP,name,speed);
        this.defaultStats = stats;
        this.invincibleFrames = invincibleFrames;
        resetStats();
    }
    @Override
    public void update() {
        super.update();
        tickInvincibility();
    }
    protected void tickInvincibility() {
        if(invincible && ++invincibleCounter > invincibleFrames) {
            invincible=false;
            invincibleCounter=0;
        }
    }


    public int damageAgainst(Character enemy){
        return Math.max(0,attack-enemy.getDefense());
    }

    public boolean takeDamage(int damage) {
        if(invincible)return false;
        currentLife = Math.max(0, currentLife - damage);
        invincible=true;
        invincibleCounter=0;
        return true;
    }

    public boolean isDefeated() {
        return currentLife <= 0;
    }
    protected void resetStats() {
        maxLife = defaultStats.getMaxLife();
        currentLife=maxLife;
        defense = defaultStats.getDefense();
        attack=defaultStats.getAttack();
    }
    protected void increaseStats(int life,int att,int def) {
        maxLife+=life; attack+=att; defense+=def;
    }


    public void restoreStats(int maxLife,int currentLife,int att,int def) {
        this.maxLife=maxLife;
        this.defense=def;
        this.attack=att;
        setCurrentLife(currentLife);
    }
    public void setCurrentLife(int life) {
        currentLife= Math.clamp(life, 0, maxLife);
    }

    public int getMaxLife() {
        return maxLife;
    }

    public int getCurrentLife() {
        return currentLife;
    }

    public int getDefense() {
        return defense;
    }

    public int getAttack() {
        return attack;
    }

    public boolean isInvincible() {
        return invincible;
    }

}