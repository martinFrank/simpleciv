package com.github.martinfrank.simpleciv.game;

public interface ProductionTemplate {

    double getProductionCosts();

    void deploy(Player owner, Settlement settlement);
}
