package it.unicam.cs.mpgc.rpg126043.entity.monster;

import it.unicam.cs.mpgc.rpg126043.entity.*;
import it.unicam.cs.mpgc.rpg126043.interfaces.CombatService;
import it.unicam.cs.mpgc.rpg126043.interfaces.EntityContext;
/** Green Slime: moves randomly and turns in the direction of the hit received. */
public class GreenSlime extends Monster {
    //CONSTANTS
    private static final int SPEED=1,REWARD_EXP=5;
    private static final Stats STATS =new Stats(10,12,6);
    private static final int AREA_X=3,AREA_Y=18,AREA_WIDTH=42,AREA_HEIGHT=30;


    private final RandomWalk walk= new RandomWalk();

    public GreenSlime(EntityContext entityContext, CombatService combatService) {
        super(entityContext,combatService, "Green Slime", SPEED, STATS,REWARD_EXP);
        setSolidArea(AREA_X,AREA_Y,AREA_WIDTH,AREA_HEIGHT);
        int tileSize=entityContext.getTileSize();
        sprites=new SpriteSet();
        sprites.setAll(loadImage("/Enemy/Images/greenslime_down_1",tileSize,tileSize),
                loadImage("/Enemy/Images/greenslime_down_2",tileSize,tileSize));
    }

    @Override
    protected void setAction(){ direction=walk.next(direction);}

    @Override
    public void damageReaction(Direction hitDirection){
        walk.reset();
        direction=hitDirection;
    }
}
