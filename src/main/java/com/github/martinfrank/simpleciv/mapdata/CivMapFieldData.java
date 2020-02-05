package com.github.martinfrank.simpleciv.mapdata;

import com.github.martinfrank.simpleciv.game.CultureMap;
import com.github.martinfrank.simpleciv.game.Player;
import com.github.martinfrank.simpleciv.game.Settlement;
import com.github.martinfrank.simpleciv.game.Unit;

import java.util.ArrayList;
import java.util.List;

public class CivMapFieldData {

    private Settlement settlement;
    private Player owner;
    private List<Unit> units = new ArrayList<>();
    private CultureMap cultureMap = new CultureMap();

    public double getWalkCostFactor() {
        return 1;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }

    public Settlement getSettlement() {
        return settlement;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "settlement: " + settlement + " owner:" + owner + " [culture:" + cultureMap + "]";
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void clearCulture() {
        cultureMap.clear();
    }

    public void setOwnerByCulture() {
        //fixme - prevent setting culture from settlement
        if (settlement == null) {
            Player mostCultivate = cultureMap.getmostCultivate();
            setOwner(mostCultivate);
        }
    }

    public CultureMap getCultureMap() {
        return cultureMap;
    }
}
