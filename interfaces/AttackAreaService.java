package it.unicam.cs.mpgc.rpg126043.interfaces;

import it.unicam.cs.mpgc.rpg126043.entity.Entity;

import java.awt.*;

public interface AttackAreaService {
    int checkArea(Rectangle area, Entity[] targets);
}
