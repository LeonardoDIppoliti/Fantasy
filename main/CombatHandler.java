package it.unicam.cs.mpgc.rpg126043.main;

import it.unicam.cs.mpgc.rpg126043.entity.Player;
import it.unicam.cs.mpgc.rpg126043.interfaces.CombatListener;
import it.unicam.cs.mpgc.rpg126043.interfaces.CombatService;
import it.unicam.cs.mpgc.rpg126043.entity.monster.Monster;

import java.util.function.Supplier;
/** Combat rules: damage, monster death, experience. Notifies the UI via a listener. */
public class CombatHandler implements CombatService {
    private final Supplier<Player> playerSupplier;
    private CombatListener combatListener = new CombatListener() {};

    public CombatHandler(Supplier<Player> playerSupplier) {
        this.playerSupplier = playerSupplier;
    }

    /*
    * After death the monster remains invincible and "dying":
    * subsequent hits from the same attack window are discarded by takeDamage()
    * */
    @Override
    public void playerHitMonster(Monster monster){
        if(monster==null){return;}
        Player player=playerSupplier.get();
        int damage=player.damageAgainst(monster);
        if(!monster.takeDamage(damage)) {return;}

        combatListener.damageDealt(damage);
        monster.damageReaction(player.getDirection());
         if(!monster.isDefeated()){ return;}

         monster.startDying();
         combatListener.monsterKilled(monster.getName(), monster.getRewardExp());
         if(player.checkLevelUp(monster.getRewardExp())){
            combatListener.playerLeveledUp(player.getLevel());
         }



    }
    @Override
    public void monsterTouchedPlayer(Monster monster){
    if(monster == null|| !monster.isAlive() || monster.isDying()){return;}
        Player player=playerSupplier.get();
    player.takeDamage(monster.damageAgainst(player));
    }


    public void setCombatListener(CombatListener listener){
        this.combatListener=listener;
    }
}
