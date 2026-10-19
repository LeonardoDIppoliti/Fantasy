package it.unicam.cs.mpgc.rpg126043.Entity;

import java.util.Random;

public class RandomWalk {
    private final int framesBetweenChanges = 120;
    private final Random random = new Random();
    private int counter;
    public Direction next(Direction currentDir){
        if(++counter<framesBetweenChanges)
            return currentDir;
            reset();
            return  Direction.random(random);
    }
    public void reset(){
        counter=0;
    }
}
