package com.github.martinfrank.simpleciv.map;

import com.github.martinfrank.maplib.MapFactory;
import com.github.martinfrank.maplib.MapPartFactory;

public class SimpleCivMapFactory extends MapFactory<SimpleCivMap, SimpleCivMapField, SimpleCivMapEdge, SimpleCivMapNode, SimpleCivMapWalker> {

    public SimpleCivMapFactory(MapPartFactory<SimpleCivMap, SimpleCivMapField, SimpleCivMapEdge, SimpleCivMapNode, SimpleCivMapWalker> mapPartFactory) {
        super(mapPartFactory);
    }
}
