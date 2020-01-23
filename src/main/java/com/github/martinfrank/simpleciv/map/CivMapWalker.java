package com.github.martinfrank.simpleciv.map;

import com.github.martinfrank.maplib.MapWalker;

import java.util.List;

public class CivMapWalker extends MapWalker<CivMapField, CivMapEdge, CivMapNode> {

    @Override
    public boolean canEnter(CivMapField from, CivMapField into) {
        return true;
    }

    @Override
    public int getEnterCosts(CivMapField from, CivMapField into) {
        return (int) into.getData().getWalkCostFactor() * 10;
    }

    @Override
    public List<CivMapField> getNeighbours(CivMapField field) {
        return getNeighboursFromEdges(field);
    }
}
