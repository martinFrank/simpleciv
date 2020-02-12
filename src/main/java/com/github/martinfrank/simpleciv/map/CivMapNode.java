package com.github.martinfrank.simpleciv.map;

import com.github.martinfrank.maplib.MapNode;
import com.github.martinfrank.simpleciv.mapdata.CivMapNodeData;

public class CivMapNode extends MapNode<CivMapNodeData, CivMapField, CivMapEdge, CivMapNode> {

    public CivMapNode(CivMapNodeData mapNodeData) {
        super(mapNodeData);
    }

}
