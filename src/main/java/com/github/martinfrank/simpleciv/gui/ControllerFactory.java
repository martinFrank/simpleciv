package com.github.martinfrank.simpleciv.gui;

import com.github.martinfrank.simpleciv.game.CivGame;
import javafx.util.Callback;

public class ControllerFactory implements Callback<Class<?>, Object> {

    private final CivGame game;

    public ControllerFactory(CivGame game) {
        this.game = game;
    }

    @Override
    public Object call(Class<?> type) {
        if (type == RootController.class) {
            RootController rootController = new RootController(game);
            game.setRootController(rootController);
            return rootController;
        } else {
            // default behavior for controllerFactory:
            try {
                return type.getDeclaredConstructor().newInstance();
            } catch (Exception exc) {
                exc.printStackTrace();
                throw new RuntimeException(exc); // fatal, just bail...
            }
        }
    }

}
