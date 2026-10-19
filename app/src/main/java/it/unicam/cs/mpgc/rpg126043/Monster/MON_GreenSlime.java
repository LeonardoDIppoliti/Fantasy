package it.unicam.cs.mpgc.rpg126043.Monster;

import it.unicam.cs.mpgc.rpg126043.Entity.*;
import it.unicam.cs.mpgc.rpg126043.Main.GamePanel;

public class MON_GreenSlime extends Monster {
    private final Stats stats= new Stats(10,12,6);
    private final RandomWalk walk= new RandomWalk();
    public MON_GreenSlime(GamePanel gP) {
        super(gP, "Green Slime", 1, new Stats(10,12,6),5);
        setSolidArea(3,18,42,30);
        int tileSize=gP.getTileSize();
        sprites=new SpriteSet();
        sprites.setAll(loadImage("/Enemy/Images/greenslime_down_1",tileSize,tileSize),
                loadImage("/Enemy/Images/greenslime_down_2",tileSize,tileSize));
    }

    @Override
    protected void setAction(){ direction=walk.next(direction);}

    @Override
    public void damageReaction(){
        walk.reset();
        direction=gP.player.getDirection();
    }
}
