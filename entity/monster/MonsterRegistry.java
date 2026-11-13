package it.unicam.cs.mpgc.rpg126043.entity.monster;

import it.unicam.cs.mpgc.rpg126043.interfaces.CombatService;
import it.unicam.cs.mpgc.rpg126043.interfaces.EntityContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

/** Monster Type Registry: creates a monster from the name and gets the name from a monster (for saving). */
public class MonsterRegistry {
    public static final String SLIME = "slime";

    private final Map<String, BiFunction<EntityContext, CombatService, Monster>> factories = new HashMap<>();
    private final Map<Class<? extends Monster>, String> names = new HashMap<>();

    public MonsterRegistry(){
        register(SLIME, GreenSlime.class, GreenSlime::new);
    }

    public void register(String monsterName, Class<? extends Monster> monsterClass,
                         BiFunction<EntityContext,CombatService,Monster> factory){

        factories.put(monsterName,factory);
        names.put(monsterClass,monsterName);
    }

    public Optional<Monster> createMonster(String monsterName, EntityContext entityContext,CombatService combatService){
        return  Optional.ofNullable(factories.get(monsterName))
                .map(biFunction->biFunction.apply(entityContext,combatService));
    }

    public Optional<String> typeOfMonster(Monster monster){
        return Optional.ofNullable(names.get(monster.getClass()));
    }

}
