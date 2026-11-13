package it.unicam.cs.mpgc.rpg126043.interfaces;

public interface GameSession {
    void newGame();
    boolean loadGame();
    void retry();
    void backToTitle();
    void quit();
}
