package com.github.martinfrank.simpleciv.map;

import com.github.martinfrank.maplib.Map;
import com.github.martinfrank.maplib.MapStyle;
import com.github.martinfrank.simpleciv.mapdata.SimpleCivMapData;

public class SimpleCivMap extends Map<SimpleCivMapData, SimpleCivMapField, SimpleCivMapEdge, SimpleCivMapNode, SimpleCivMapWalker> {


    public SimpleCivMap(int width, int height, MapStyle style, SimpleCivMapData mapData) {
        super(width, height, style, mapData);
    }

    @Override
    public void draw(Object drawContext) {
        getFields().forEach(f -> f.draw(drawContext));
    }

}
