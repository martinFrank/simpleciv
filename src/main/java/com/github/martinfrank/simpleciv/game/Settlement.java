package com.github.martinfrank.simpleciv.game;

import com.github.martinfrank.simpleciv.map.CivMapField;

import java.util.ArrayList;
import java.util.List;

public class Settlement {

    private final Player owner;
    private final CivMapField field;
    private final List<SettlementImprovement> improvements = new ArrayList<>();
    private CurrentWork currentWork = new CurrentWork();
    public double culture = 10; //FIXME
    private double productivity = 10;
    private double residents = 1000;
    private double foodConsumption = 10;

    public Settlement(Player owner, CivMapField field) {
        this.owner = owner;
        this.field = field;
    }

    @Override
    public String toString() {
        return "capital, owner:" + owner;
    }

    public boolean isOwnedBy(Player player) {
        if (owner == null) {
            return player != null;
        }
        return owner.equals(player);
    }

    public CivMapField getField() {
        return field;
    }

    public List<UnitTemplate> getUnitTypes() {
        List<UnitTemplate> unitTypes = new ArrayList<>();
        unitTypes.add(UnitTemplate.WARRIOR);
        return unitTypes;
    }

    public void setProduction(ProductionTemplate productionTemplate) {
        currentWork.setProduction(productionTemplate);
    }

    public void addProductivity() {
        currentWork.add(productivity);
        if (currentWork.isFinished()) {
            currentWork.deploy(owner, this);
            //FIXME remove currentWork
        }
    }

    public double getCulture() {
        return culture;
    }

}
