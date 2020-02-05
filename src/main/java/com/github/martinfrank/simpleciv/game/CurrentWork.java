package com.github.martinfrank.simpleciv.game;

public class CurrentWork {

    private ProductionTemplate productionTemplate;

    private double progress;

    public void setProduction(ProductionTemplate unitType) {
        //resetAll();
        this.productionTemplate = unitType;
    }

    public void add(double productivity) {
        this.progress = progress + productivity;
    }

    public boolean isFinished() {
        if (productionTemplate != null) {
            return progress >= productionTemplate.getProductionCosts();
        }
        return false;
    }

    public void deploy(Player owner, Settlement settlement) {
        productionTemplate.deploy(owner, settlement);
    }
}
