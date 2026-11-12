package it.unicam.cs.mpgc.rpg126043.entity;

/** Melee attack state machine: loading, active hit, end. */
public class MeleeAttack {
    private static final int TOTAL_FRAMES =25, ACTIVE_FRAME = 5;
    private int frame;
    private boolean active;

    public void startActive(){
        active=true;
        frame=0;
    }
    public void cancelActive(){
        active=false;
        frame=0;
    }

    public void tick(){
        if(active && ++frame > TOTAL_FRAMES) {cancelActive();}
    }

    public boolean isActive(){
            return active;
    }
    public boolean isHitting(){
        return active && frame> ACTIVE_FRAME;
    }
    public int spriteNum(){
        return frame > ACTIVE_FRAME ? 2:1;    }
}
