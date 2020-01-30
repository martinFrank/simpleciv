package com.github.martinfrank.simpleciv;

import com.github.martinfrank.simpleciv.game.Game;
import com.github.martinfrank.simpleciv.gui.ControllerFactory;
import com.github.martinfrank.simpleciv.gui.RootController;
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

    private Game game;
    private RootController rootController;
    private Pane root;

    @Override
    public void init() {
        ResourceManager resourceManager = new ResourceManager(getClass().getClassLoader());
        game = new Game(resourceManager);//model
        ControllerFactory controllerFactory = new ControllerFactory(game);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(resourceManager.getGuiRoot());
            fxmlLoader.setControllerFactory(controllerFactory); //new Controller(model)
            root = fxmlLoader.load();
        } catch (IOException e) {
            LOGGER.debug("error", e);
        }


//        Model m = new Model();
//        Controller c = new Controller(m);
//        InputViewForm i = new InputViewForm(c);
//        OutputViewForm f = new OutputViewForm(m);
//        c.register(f.update);  // note that  f.update  has type  Observer
//        ...
//        Application.Run(i);  // give control to the input view --- it's now a reactive system
    }

    @Override
    public void start(Stage stage) {
        if (root != null) {
            stage.setScene(new Scene(root));
            stage.setTitle("tbd: set title");
            stage.show();
            game.init();
        } else {
            LOGGER.debug("error during init");
            Platform.exit();
            System.exit(0);
        }
    }

}
