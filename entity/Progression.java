package it.unicam.cs.mpgc.rpg126043.entity;

/** Player level, experience, and level threshold. */
public class Progression {
    private static final int FIRST_LEVEL = 1, INITIAL_EXP_REQ = 10, EXP_REQ_GROWTH = 2;
    private int level,exp,nextLevelExp;

    public Progression()
    {
        reset();
    }

    protected void reset(){
        restore(FIRST_LEVEL,0, INITIAL_EXP_REQ);
    }
    protected void restore(int level, int exp, int nextLevelExp){
        this.level=level;
        this.exp=exp;
        this.nextLevelExp=nextLevelExp;
    }

    protected boolean gainExp(int amount){
        exp +=amount;
        if(exp<nextLevelExp){return false;}
        level++;
        nextLevelExp *= EXP_REQ_GROWTH;
        return true;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public int getNextLevelExp() {
        return nextLevelExp;
    }
}
