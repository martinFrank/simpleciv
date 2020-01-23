package com.github.martinfrank.simpleciv;

import com.github.martinfrank.simpleciv.game.CivGame;
import com.github.martinfrank.simpleciv.gui.ControllerFactory;
import com.github.martinfrank.simpleciv.res.ResourceManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class App extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        launch(args);
    }

    private CivGame civGame;
    private Pane root;


    @Override
    public void init() {
        ResourceManager resourceManager = new ResourceManager(getClass().getClassLoader());
        civGame = new CivGame(resourceManager);
        ControllerFactory controllerFactory = new ControllerFactory(civGame);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(resourceManager.getGuiRoot());
            fxmlLoader.setControllerFactory(controllerFactory);
            root = fxmlLoader.load();
        } catch (IOException e) {
            LOGGER.debug("error", e);
        }
    }

    @Override
    public void start(Stage stage) {
        if (wasInitSuccessFul()) {
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("tbd: set title");
            stage.show();
            civGame.init();
        } else {
            LOGGER.debug("error during init");
            Platform.exit();
            System.exit(0);
        }
    }

    private boolean wasInitSuccessFul() {
        boolean conditionOnRoot = root != null;
        LOGGER.debug("check root:{}, success={}", root, conditionOnRoot);
        return conditionOnRoot;
    }


}
