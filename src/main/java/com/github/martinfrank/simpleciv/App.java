package com.github.martinfrank.simpleciv;

import com.github.martinfrank.maplib.MapStyle;
import com.github.martinfrank.simpleciv.map.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class App extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        launch(args);
    }

    private SimpleCivMap simpleCivMap;
    private SimpleCivMapWalker walker;


    private SimpleCivMapField start;
    private SimpleCivMapField end;

    @Override
    public void start(Stage primaryStage) {
        SimpleCivMapPartFactory mapPartFactory = new SimpleCivMapPartFactory();
        SimpleCivMapFactory mapFactory = new SimpleCivMapFactory(mapPartFactory);
//        demoMap = mapFactory.createMap(12, 6, MapStyle.HEX_HORIZONTAL);
        simpleCivMap = mapFactory.createMap(12, 6, MapStyle.SQUARE_DIAMOND);
        simpleCivMap.scale(14f);
        walker = mapPartFactory.createWalker();

        shuffleWalkCosts();

        primaryStage.setTitle("Hello World!");
        BorderPane border = new BorderPane();
        double w = simpleCivMap.getTransformed().getWidth();
        double h = simpleCivMap.getTransformed().getHeight();
        Canvas canvas = new Canvas(w, h);
        canvas.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            int x = (int) mouseEvent.getX();
            int y = (int) mouseEvent.getY();
            SimpleCivMapNode point = simpleCivMap.getNodeAt(x, y);
            SimpleCivMapEdge edge = simpleCivMap.getEdgeAt(x, y);
            SimpleCivMapField field = simpleCivMap.getFieldAt(x, y);
            LOGGER.debug("x/y:{}/{} Point:{}", x, y, point);
            LOGGER.debug("x/y:{}/{} Edge:{}", x, y, edge);
            LOGGER.debug("x/y:{}/{} Field:{}", x, y, field);

        });

        Button btn = new Button();
        btn.setText("Shuffle Map");
        btn.setOnAction(event -> {
            shuffleWalkCosts();
            GraphicsContext gc = canvas.getGraphicsContext2D();
            drawShapes(gc);
        });
        border.setBottom(btn);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);

        border.setCenter(canvas);
        primaryStage.setScene(new Scene(border));
        primaryStage.show();
    }

    private void shuffleWalkCosts() {
        Random random = new Random();
        for (SimpleCivMapField demoMapField : simpleCivMap.getFields()) {
            demoMapField.getData().setWalkCostFactor(1d);
            demoMapField.getData().markAsPath(false);
            int die = random.nextInt(6) + 1;
            if (die == 1) {
                demoMapField.getData().setWalkCostFactor(6d);
            }
            if (die == 2) {
                demoMapField.getData().setWalkCostFactor(3d);
            }
        }
        start = null;
        end = null;
    }


    private void drawShapes(GraphicsContext gc) {
        if (simpleCivMap != null) {
            simpleCivMap.draw(gc);
        }
    }

}
