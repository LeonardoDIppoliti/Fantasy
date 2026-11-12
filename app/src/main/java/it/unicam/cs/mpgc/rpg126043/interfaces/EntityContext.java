package it.unicam.cs.mpgc.rpg126043.interfaces;

public interface EntityContext extends TileSizeProvider {
    Camera getCamera();
    MovementCollisionService getCollision();
}
