package com.github.martinfrank.simpleciv.map;

import com.github.martinfrank.drawlib.Point;
import com.github.martinfrank.drawlib.Shape;
import com.github.martinfrank.maplib.MapField;
import com.github.martinfrank.simpleciv.game.Player;
import com.github.martinfrank.simpleciv.game.Settlement;
import com.github.martinfrank.simpleciv.game.Unit;
import com.github.martinfrank.simpleciv.mapdata.CivMapFieldData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CivMapField extends MapField<CivMapFieldData, CivMapField, CivMapEdge, CivMapNode> {

    public CivMapField(CivMapFieldData mapFieldData) {
        super(mapFieldData);
    }

    @Override
    public void draw(Object drawContext) {
        GraphicsContext gc = (GraphicsContext) drawContext;
        gc.setFill(Color.GRAY);
        Player owner = getData().getOwner();
        if (owner != null) {
            gc.setFill(owner.getColor());
        }


        gc.setStroke(Color.DARKGRAY);
        gc.setLineWidth(1);

        Shape shape = getShape().getTransformed();
        double[] xs = shape.getPoints().stream().mapToDouble(Point::getX).toArray();
        double[] ys = shape.getPoints().stream().mapToDouble(Point::getY).toArray();
        int amount = xs.length;
        gc.fillPolygon(xs, ys, amount);

        getEdges().forEach(e -> e.draw(drawContext));
        getNodes().forEach(p -> p.draw(drawContext));

        Settlement settlement = getData().getSettlement();
        if (settlement != null) {
            Point point = shape.getCenter();
            double minW = shape.getPoints().stream().mapToDouble(Point::getX).min().orElse(0);
            double maxW = shape.getPoints().stream().mapToDouble(Point::getX).max().orElse(0);

            double minH = shape.getPoints().stream().mapToDouble(Point::getY).min().orElse(0);
            double maxH = shape.getPoints().stream().mapToDouble(Point::getY).max().orElse(0);
            double w = (maxW - minW) / 4d;
            double h = (maxH - minH) / 4d;
            gc.setFill(Color.BLACK);
            gc.fillOval(minW + w, minH + h, 2d * w, 2d * h);
        }

        if (!getData().getUnits().isEmpty()) {
            //FIXME draw unit

            Point point = shape.getCenter();
            double minW = shape.getPoints().stream().mapToDouble(Point::getX).min().orElse(0);
            double maxW = shape.getPoints().stream().mapToDouble(Point::getX).max().orElse(0);

            double minH = shape.getPoints().stream().mapToDouble(Point::getY).min().orElse(0);
            double maxH = shape.getPoints().stream().mapToDouble(Point::getY).max().orElse(0);
            double w = (maxW - minW) / 8d;
            double h = (maxH - minH) / 8d;
            gc.setFill(Color.YELLOW);
            gc.fillOval(minW + w, minH + h, 4d * w, 4d * h);
        }

    }

    @Override
    public String toString() {
        return getData().toString();
    }

    public boolean isOwnedBy(Player player) {
        return getData() != null && getData().getOwner() != null && getData().getOwner().equals(player);
    }

    public List<Unit> getUnits(Player player) {
        if (getData() != null && getData().getUnits() != null) {
            return getData().getUnits().stream().filter(u -> u.getOwner().equals(player)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public Settlement getSettlement() {
        if (getData() != null && getData().getSettlement() != null) {
            return getData().getSettlement();
        }
        return null;
    }

    public void addUnit(Unit unit) {
        getData().getUnits().add(unit);
    }
}
