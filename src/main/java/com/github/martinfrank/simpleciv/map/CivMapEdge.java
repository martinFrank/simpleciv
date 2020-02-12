package com.github.martinfrank.simpleciv.map;

import com.github.martinfrank.maplib.MapEdge;
import com.github.martinfrank.simpleciv.mapdata.CivMapEdgeData;

public class CivMapEdge extends MapEdge<CivMapEdgeData, CivMapField, CivMapEdge, CivMapNode> {

    public CivMapEdge(CivMapEdgeData mapEdgeData) {
        super(mapEdgeData);
    }

}
