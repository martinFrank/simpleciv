package com.github.martinfrank.simpleciv.game;

public class UnitTemplate implements ProductionTemplate {

    public static final UnitTemplate WARRIOR = new UnitTemplate(1, 1, 10, 10);

    public final double attack;
    public final double defense;
    public final double life;
    private final double productionCosts;

    private UnitTemplate(double attack, double defense, double life, double productionCosts) {
        this.attack = attack;
        this.defense = defense;
        this.life = life;
        this.productionCosts = productionCosts;
    }

    @Override
    public double getProductionCosts() {
        return productionCosts;
    }

    @Override
    public void deploy(Player owner, Settlement settlement) {
        Unit unit = new Unit(this, owner);
        settlement.getField().addUnit(unit);
    }
}