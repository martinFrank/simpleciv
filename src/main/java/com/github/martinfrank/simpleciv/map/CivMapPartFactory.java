package com.github.martinfrank.simpleciv.map;

import com.github.martinfrank.maplib.MapPartFactory;
import com.github.martinfrank.maplib.MapStyle;
import com.github.martinfrank.simpleciv.mapdata.CivMapData;
import com.github.martinfrank.simpleciv.mapdata.CivMapEdgeData;
import com.github.martinfrank.simpleciv.mapdata.CivMapFieldData;
import com.github.martinfrank.simpleciv.mapdata.CivMapNodeData;

public class CivMapPartFactory extends MapPartFactory<CivMap, CivMapField, CivMapEdge, CivMapNode, CivMapWalker> {

    @Override
    public CivMapNode createMapNode() {
        return new CivMapNode(new CivMapNodeData());
    }

    @Override
    public CivMapEdge createMapEdge() {
        return new CivMapEdge(new CivMapEdgeData());
    }

    @Override
    public CivMapField createMapField() {
        return new CivMapField(new CivMapFieldData());
    }

    @Override
    public CivMap createMap(int columns, int rows, MapStyle style) {
        return new CivMap(columns, rows, style, new CivMapData());
    }

    @Override
    public CivMapWalker createWalker() {
        return new CivMapWalker();
    }
}
