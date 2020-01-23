package com.github.martinfrank.simpleciv.map;

import com.github.martinfrank.drawlib.Point;
import com.github.martinfrank.maplib.MapEdge;
import com.github.martinfrank.simpleciv.mapdata.CivMapEdgeData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CivMapEdge extends MapEdge<CivMapEdgeData, CivMapField, CivMapEdge, CivMapNode> {

    public CivMapEdge(CivMapEdgeData mapEdgeData) {
        super(mapEdgeData);
    }

    @Override
    public void draw(Object drawContext) {
        GraphicsContext gc = (GraphicsContext) drawContext;

        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(3);

        Point a = getLine().getTransformed().getA();
        Point b = getLine().getTransformed().getB();
        gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
    }
}
