package it.unicam.cs.mpgc.rpg126043.main;

import it.unicam.cs.mpgc.rpg126043.entity.Entity;
import it.unicam.cs.mpgc.rpg126043.entity.NPC;
import it.unicam.cs.mpgc.rpg126043.entity.Player;
import it.unicam.cs.mpgc.rpg126043.interfaces.*;
import it.unicam.cs.mpgc.rpg126043.entity.monster.Monster;
import it.unicam.cs.mpgc.rpg126043.tile.TileManager;


import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**World state: player, NPCs, and monsters, with updates and drawing sorted by depth. */
public class GameWorld implements  WorldView{
    private final NPC[] npc = new NPC[10];
    private final Monster[] monster = new Monster[20];
    private final Player player;
    private final List<Entity> entityList = new ArrayList<>();
    public GameWorld(EntityContext entityContext, CombatService combatService,
                     EventService eventService, PlayerInput playerInput, AttackAreaService attackAreaService,int screenWidth,int screenHeight){
        this.player=new Player(entityContext,this,combatService,eventService,playerInput,
                attackAreaService,screenWidth,screenHeight);
    }
    /*
    * Removes dead monsters from the array.
    *  The index is used because we need to write null in the cell;
    * assigning null to a local variable would not remove the monster.
    * */
    public void updateWorld(){
        player.update();
        for(NPC npc : npc){
            if(npc != null){
                npc.update();
            }
        }
        for(int i =0; i<monster.length; i++){
            Monster m=monster[i];
            if(m == null){continue;}
            if(m.isAlive()){m.update();}
            else{monster[i]=null;}
        }
    }
    /*
    * The entities are drawn in ascending Y: whoever is lower goes "up",
    * so the player passes "behind" whoever is further north.
    * */
    public void drawWorld(Graphics2D g2, TileManager tileManager, Camera camera){
        tileManager.draw(g2,camera);
        entityList.add(player);
        for(NPC npc : npc){
            if(npc != null){
                entityList.add(npc);
            }
        }
        for(Monster monster : monster){
            if(monster != null){
                entityList.add(monster);
            }
        }
        entityList.sort(Comparator.comparingInt(Entity::getWorldY));
        for(Entity entity : entityList){
            entity.draw(g2);
        }
        entityList.clear();
    }

    public boolean allMonsterDead(){
        for(Monster mon : monster){
            if(mon!=null) return false;
        }
        return true;
    }
    @Override
    public NPC[] getNpc() {
        return npc;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Monster[] getMonster() {
        return monster;
    }




}
