package com.github.martinfrank.simpleciv.gui;


import com.github.martinfrank.simpleciv.map.CivMapEdge;
import com.github.martinfrank.simpleciv.map.CivMapField;
import com.github.martinfrank.simpleciv.map.CivMapNode;

public class MouseSelection {

    private final int x;
    private final int y;
    private CivMapNode node;
    private CivMapEdge edge;
    private CivMapField field;

    public MouseSelection(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public CivMapNode getNode() {
        return node;
    }

    public void setNode(CivMapNode node) {
        this.node = node;
    }

    public boolean hasNode() {
        return node != null;
    }

    public CivMapEdge getEdge() {
        return edge;
    }

    public void setEdge(CivMapEdge edge) {
        this.edge = edge;
    }

    public boolean hasEdge() {
        return edge != null;
    }

    public CivMapField getField() {
        return field;
    }

    public void setField(CivMapField field) {
        this.field = field;
    }

    public boolean hasField() {
        return field != null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "mouse selection @ " + x + "/" + y + " field='" + field + "', edge='" + edge + "', node='" + node + "'.";
    }
}
