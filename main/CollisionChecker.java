package it.unicam.cs.mpgc.rpg126043.main;

import it.unicam.cs.mpgc.rpg126043.entity.Entity;
import it.unicam.cs.mpgc.rpg126043.interfaces.AttackAreaService;
import it.unicam.cs.mpgc.rpg126043.interfaces.MovementCollisionService;
import it.unicam.cs.mpgc.rpg126043.interfaces.WorldView;
import it.unicam.cs.mpgc.rpg126043.tile.TileManager;

import java.awt.*;
import java.util.function.Supplier;
/** Detects collisions with tiles, entities, and attack areas without changing the entities' state. */
public class CollisionChecker implements MovementCollisionService, AttackAreaService {
    private final TileManager tileManager;
    private final int tileSize;
    private final Supplier<WorldView> worldView;

    public CollisionChecker(TileManager tileManager, int tileSize, Supplier<WorldView> worldView){
        this.tileManager=tileManager;
        this.tileSize=tileSize;
        this.worldView= worldView;
    }

    @Override
    public void checkTile(Entity e){
        if(isBlockedByTile(e.getBoundsAfterMove())){ e.setCollisionOn(true);
        }
    }

    @Override
    public int checkNpc(Entity entity) {
        return checkEntity(entity, worldView.get().getNpc());
    }

    @Override
    public int checkMonster(Entity entity) {
        return checkEntity(entity, worldView.get().getMonster());
    }

    /*
    * Controls each tile touched by the area since a character
    * wider than a tile could pass through a slim wall
    * floorDiv its used  instead of normal division since its approximates to 0
    * and could fail with negative coordinates
    * (the outside of the map is treated like a wall)
    * */
    private boolean isBlockedByTile(Rectangle area){

        int left= Math.floorDiv(area.x,tileSize);
        int right = Math.floorDiv(area.x + area.width- 1,tileSize);
        int top = Math.floorDiv(area.y,tileSize);
        int bottom = Math.floorDiv(area.y + area.height -1,tileSize);

        for(int col = left;col<=right;col++){
            for(int row = top; row<= bottom;row++){
                if(tileManager.isSolid(col,row)){return  true;}
            }
        }
        return false;
    }


    /*
    * report the contact by returning the index, but block movement
    * only if the step creates a NEW overlap. if the entities are
    * already touching, the one moving can move away, without
    * this distinction it would remain stuck for as long the
    * contact lasts
    * */
    private int checkEntity(Entity e,Entity[] target){
        Rectangle currentArea=e.getBounds();
        Rectangle nextArea = e.getBoundsAfterMove();
        for(int i =0;i<target.length;i++){
            Entity entityTarget = target[i];
            if(entityTarget == null || entityTarget == e || !nextArea.intersects(entityTarget.getBounds())){
                continue;
            }
            if(!currentArea.intersects(entityTarget.getBounds())){
                e.setCollisionOn(true);
            }
            return i;
        }
        return NOT_HIT;
    }

    //Entity to Player collision
    @Override
    public boolean checkPlayer(Entity e){
        if(e.getBoundsAfterMove().intersects(worldView.get().getPlayer().getBounds())){
            e.setCollisionOn(true);
            return true;
        }
        return false;
    }

    //Attack area collision
    @Override
    public int checkArea(Rectangle area, Entity[] targets){
        for (int i =0; i < targets.length;i++){
            if(targets[i] != null && area.intersects(targets[i].getBounds())){
                return i;
            }
        }
        return NOT_HIT;
    }
}
