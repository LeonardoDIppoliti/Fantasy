package it.unicam.cs.mpgc.rpg126043.interfaces;

public interface PlayerInput {
    boolean isUpPressed();
    boolean isDownPressed();
    boolean isLeftPressed();
    boolean isRightPressed();
    boolean hasAction();
    boolean consumeAction();
}
