package com.github.martinfrank.simpleciv.game;

public class Unit {

    private final UnitTemplate unitType;

    private final Player owner;
    private double currentLife;

    public Unit(UnitTemplate unitType, Player owner) {
        this.unitType = unitType;
        this.owner = owner;
        currentLife = unitType.life;
    }

    public Player getOwner() {
        return owner;
    }
}
