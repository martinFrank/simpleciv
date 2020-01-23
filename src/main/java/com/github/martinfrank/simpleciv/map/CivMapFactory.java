package com.github.martinfrank.simpleciv.map;

import com.github.martinfrank.maplib.MapFactory;
import com.github.martinfrank.maplib.MapPartFactory;

public class CivMapFactory extends MapFactory<CivMap, CivMapField, CivMapEdge, CivMapNode, CivMapWalker> {

    public CivMapFactory(MapPartFactory<CivMap, CivMapField, CivMapEdge, CivMapNode, CivMapWalker> mapPartFactory) {
        super(mapPartFactory);
    }
}
