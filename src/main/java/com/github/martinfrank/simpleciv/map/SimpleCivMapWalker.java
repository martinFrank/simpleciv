package com.github.martinfrank.simpleciv.map;

import com.github.martinfrank.maplib.MapWalker;

import java.util.List;

public class SimpleCivMapWalker extends MapWalker<SimpleCivMapField, SimpleCivMapEdge, SimpleCivMapNode> {

    @Override
    public boolean canEnter(SimpleCivMapField from, SimpleCivMapField into) {
        return true;
    }

    @Override
    public int getEnterCosts(SimpleCivMapField from, SimpleCivMapField into) {
        return (int) into.getData().getWalkCostFactor() * 10;
    }

    @Override
    public List<SimpleCivMapField> getNeighbours(SimpleCivMapField field) {
        return getNeighboursFromEdges(field);
    }
}
