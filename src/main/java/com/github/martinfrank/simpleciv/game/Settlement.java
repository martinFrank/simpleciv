package com.github.martinfrank.simpleciv.game;

import com.github.martinfrank.simpleciv.map.CivMapField;

import java.util.ArrayList;
import java.util.List;

public class Settlement {

    private final Player owner;
    private final CivMapField field;
    private final List<SettlementImprovement> improvements = new ArrayList<>();
    //FIXME maybe UnitType is not sufficent enough
    private CurrentWork currentWork = new CurrentWork();
    private double productvity = 10;
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

    //FIXME maybe UnitType is not sufficent enough
    public void setProduction(UnitTemplate unitType) {
        currentWork.setProduction(unitType);
    }

    public void addProductivity() {
        currentWork.add(productvity);
        if (currentWork.isFinished()) {
            currentWork.deploy(owner, this);
            //FIXME remove currentWork
        }
    }
}
