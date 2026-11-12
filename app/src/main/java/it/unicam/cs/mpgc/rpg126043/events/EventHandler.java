package it.unicam.cs.mpgc.rpg126043.events;

import it.unicam.cs.mpgc.rpg126043.entity.Direction;
import it.unicam.cs.mpgc.rpg126043.entity.Player;
import it.unicam.cs.mpgc.rpg126043.interfaces.EventService;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
/** Set of game events (effect zones) triggered at each player step. */
public class EventHandler implements EventService {
    private static final int ZONE_OFF_SET = 23 , ZONE_SIZE =8;

    private final int tileSize;
    private final Supplier<Player> player;
    private final List<GameEvent> events = new ArrayList<>();


    public EventHandler (int tileSize, Supplier<Player> player) {
        this.tileSize=tileSize;
        this.player=player;
    }
    /*
    * Registers a region of the world with an effect.
    * Events are given; adding one doesn't require changing checkEvent().
    * The region is small and offset within the tile,
    * so the player must get close to it.
    * */
    public void registerEvent(int col, int row,Direction required,
                         boolean needsAction, Runnable effect){
         Rectangle zone = new Rectangle(col * tileSize+ ZONE_OFF_SET,row*tileSize+ ZONE_OFF_SET, ZONE_SIZE, ZONE_SIZE);
         events.add(new GameEvent(zone,required,needsAction,effect));
    }
    /*
    * Returns true only if the event used the player action
    * so Player won't always attack by pressing ENTER
    * */
    @Override
    public boolean checkEvent(boolean action) {
        Player player1 = player.get();
        Rectangle bounds = player1.getBounds();
        boolean consumed = false;
        for(GameEvent event : events){
            if(event.tryFire(bounds,player1.getDirection(),action) && event.isNeedsAction()){
                consumed = true;
            }
        }
        return consumed;
    }

}
