package com.github.martinfrank.simpleciv.gui;

import com.github.martinfrank.simpleciv.map.CivMap;
import javafx.scene.canvas.Canvas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CivMapCanvas extends Canvas {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(CivMapCanvas.class);

    private CivMap map;

    public void setMap(CivMap map) {
        this.map = map;
        int h = (int) map.getTransformed().getHeight();
        int w = (int) map.getTransformed().getWidth();
        setHeight(h);
        setWidth(w);
//        Shape aField = map.getFields().get(0).getShape();
//        double fieldHeight = aField.getTransformed().getHeight();
//        setWidth(map.getTransformed().getWidth());
//        if (map.getRows() % 2 == 1) {
//            setHeight(map.getTransformed().getHeight() - fieldHeight * 1.5);
//        } else {
//            setHeight(map.getTransformed().getHeight());
//        }
        drawMap();
    }

    void drawMap() {
        if (map != null) {
            map.draw(getGraphicsContext2D());
        }
    }


    public MouseSelection getSelectionAt(int x, int y) {
        MouseSelection selection = new MouseSelection(x, y);
        if (map != null) {
            selection.setNode(map.getNodeAt(x, y));
            selection.setEdge(map.getEdgeAt(x, y));
            selection.setField(map.getFieldAt(x, y));
        }
        return selection;
    }

}
