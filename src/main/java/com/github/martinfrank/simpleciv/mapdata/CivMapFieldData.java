package com.github.martinfrank.simpleciv.mapdata;

import com.github.martinfrank.simpleciv.game.Player;
import com.github.martinfrank.simpleciv.game.Settlement;

public class CivMapFieldData {

    private Settlement settlement;
    private Player owner;

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
        return "settlement: " + settlement + " owner:" + owner;
    }
}
