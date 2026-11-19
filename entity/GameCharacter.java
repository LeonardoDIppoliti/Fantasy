package it.unicam.cs.mpgc.rpg126043.entity;

import it.unicam.cs.mpgc.rpg126043.interfaces.EntityContext;


/*
* Entities with stats (health, attack, defense) and temporary invincibility
* after getting hit
* */
public abstract class GameCharacter extends Entity {
    //CHARACTERS ATTRIBUTES
    private final Stats defaultStats;
    private final int invincibleFrames;
    private int maxLife, currentLife, defense, attack;
    private boolean invincible;
    private int invincibleCounter;



    public GameCharacter(EntityContext entityContext, String name, int speed, Stats stats, int invincibleFrames) {
        super(entityContext,name,speed);
        this.defaultStats = stats;
        this.invincibleFrames = invincibleFrames;
        resetStats();
    }
    //LOGIC SECTION
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

    //COMBAT SECTION
    public int damageAgainst(GameCharacter enemy){
        return Math.max(0,attack-enemy.getDefense());
    }

    /*
    * Applies damage only if it isn't invincible
    * it says if the hit occurred.
    * Without invincibility the hit (valued each frame for 20 frame)
    * would remove health more times it's supposed to do
    * */
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


    //STATS SECTION

    /*
    *Restore player stats from a savefile.
    * The order: first maxLife and after currentLife, is important
    * since getCurrentLife() limits the value between 0 and max
    *  */
    protected final void resetStats() {
        maxLife = defaultStats.maxLife();
        currentLife=maxLife;
        defense = defaultStats.defense();
        attack=defaultStats.attack();
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

    //ACCESS GETTER/SETTER SECTION
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