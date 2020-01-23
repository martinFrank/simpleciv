package com.github.martinfrank.simpleciv.map;

import com.github.martinfrank.maplib.Map;
import com.github.martinfrank.maplib.MapStyle;
import com.github.martinfrank.simpleciv.mapdata.CivMapData;

public class CivMap extends Map<CivMapData, CivMapField, CivMapEdge, CivMapNode, CivMapWalker> {


    public CivMap(int width, int height, MapStyle style, CivMapData mapData) {
        super(width, height, style, mapData);
    }

    @Override
    public void draw(Object drawContext) {
        getFields().forEach(f -> f.draw(drawContext));
    }

}