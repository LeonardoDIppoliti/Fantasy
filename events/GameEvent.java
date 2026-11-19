package it.unicam.cs.mpgc.rpg126043.events;

import it.unicam.cs.mpgc.rpg126043.entity.Direction;

import java.awt.*;
/** Zone of the world that pops when the player touches it, optionally with direction and action key. */
public class GameEvent {
    private final Rectangle zone;
    private final Direction dirRequired;
    private final boolean needsAction;
    private final Runnable effect;
    private boolean inside;

    public GameEvent(Rectangle zone, Direction dirRequired, boolean needsAction, Runnable effect) {
        this.zone = zone;
        this.dirRequired = dirRequired;
        this.needsAction = needsAction;
        this.effect = effect;
    }

    public boolean tryFire(Rectangle player,Direction facing, boolean action){
        boolean touching = zone.intersects(player) && (dirRequired ==null || dirRequired==facing ) ;
        boolean fire = touching && (needsAction ? action : !inside);
        inside=touching;
        if(fire){
            effect.run();
        }
        return fire;
    }

    public boolean isNeedsAction() {
        return needsAction;
    }
}
