package it.unicam.cs.mpgc.rpg126043.interfaces;

public interface InputState {
    boolean consumeUp();
    boolean consumeDown();
    boolean consumeEnter();
    boolean consumeEsc();
    void clearEvents();
}
