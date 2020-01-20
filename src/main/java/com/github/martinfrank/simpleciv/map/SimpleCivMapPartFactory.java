package com.github.martinfrank.simpleciv.map;

import com.github.martinfrank.maplib.MapPartFactory;
import com.github.martinfrank.maplib.MapStyle;
import com.github.martinfrank.simpleciv.mapdata.SimpleCivMapData;
import com.github.martinfrank.simpleciv.mapdata.SimpleCivMapEdgeData;
import com.github.martinfrank.simpleciv.mapdata.SimpleCivMapFieldData;
import com.github.martinfrank.simpleciv.mapdata.SimpleCivMapNodeData;

public class SimpleCivMapPartFactory extends MapPartFactory<SimpleCivMap, SimpleCivMapField, SimpleCivMapEdge, SimpleCivMapNode, SimpleCivMapWalker> {

    @Override
    public SimpleCivMapNode createMapNode() {
        return new SimpleCivMapNode(new SimpleCivMapNodeData());
    }

    @Override
    public SimpleCivMapEdge createMapEdge() {
        return new SimpleCivMapEdge(new SimpleCivMapEdgeData());
    }

    @Override
    public SimpleCivMapField createMapField() {
        return new SimpleCivMapField(new SimpleCivMapFieldData());
    }

    @Override
    public SimpleCivMap createMap(int columns, int rows, MapStyle style) {
        return new SimpleCivMap(columns, rows, style, new SimpleCivMapData());
    }

    @Override
    public SimpleCivMapWalker createWalker() {
        return new SimpleCivMapWalker();
    }
}
