package it.unicam.cs.mpgc.rpg126043.entity;

import java.util.Random;
/** The four directions with their relative displacement on the axes, used instead of strings. */
public enum Direction {
    UP(0,-1),
    DOWN(0,1),
    LEFT(-1,0),
    RIGHT(1,0),
    ;
    private final int dX,dY;

    Direction(int dX, int dY) {
        this.dX = dX;
        this.dY = dY;
    }
    public Direction opposite() {
      return   switch (this) {
          case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
      };

    }

    public String key(){
        return name().toLowerCase();
    }
    public static Direction random(Random r){
        return values()[r.nextInt(values().length)];
    }

    public int getDx() {
        return dX;
    }

    public int getDy() {
        return dY;
    }
}
