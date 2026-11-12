package it.unicam.cs.mpgc.rpg126043.entity;

import java.util.Random;
/** Changes direction randomly at regular intervals: behavior shared by NPCs and monsters. */
public class RandomWalk {
    private static final int FRAMES_BETWEEN_CHANGES = 120;
    private final Random random = new Random();
    private int counter;
    public Direction next(Direction currentDir){
        if(++counter< FRAMES_BETWEEN_CHANGES){
            return currentDir;}
            reset();
            return  Direction.random(random);
    }
    public void reset(){
        counter=0;
    }
}
