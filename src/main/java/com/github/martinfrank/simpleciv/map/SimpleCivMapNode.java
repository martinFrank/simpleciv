package com.github.martinfrank.simpleciv.map;

import com.github.martinfrank.drawlib.Point;
import com.github.martinfrank.maplib.MapNode;
import com.github.martinfrank.simpleciv.mapdata.SimpleCivMapNodeData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SimpleCivMapNode extends MapNode<SimpleCivMapNodeData, SimpleCivMapField, SimpleCivMapEdge, SimpleCivMapNode> {

    public SimpleCivMapNode(SimpleCivMapNodeData mapNodeData) {
        super(mapNodeData);
    }

    @Override
    public void draw(Object drawContext) {
        GraphicsContext gc = (GraphicsContext) drawContext;

        gc.setStroke(Color.RED);
        gc.setLineWidth(5);
        Point point = getPoint().getTransformed();
        gc.strokeLine(point.getX(), point.getY(), point.getX(), point.getY());
    }
}
