package com.github.martinfrank.simpleciv.game;

import com.github.martinfrank.maplib.MapStyle;
import com.github.martinfrank.simpleciv.gui.GuiEventListener;
import com.github.martinfrank.simpleciv.gui.MouseSelection;
import com.github.martinfrank.simpleciv.gui.RootController;
import com.github.martinfrank.simpleciv.map.*;
import com.github.martinfrank.simpleciv.res.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class CivGame implements GuiEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CivGame.class);

    private CivMap civMap;
    private Players players;
    private CivMapWalker walker;
    private final Random random = new Random();
    private final ResourceManager resourceManager;
    private RootController rootController;

    public CivGame(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    private void setupPlayer(Player current) {
        int size = civMap.getFields().size();
        CivMapField field = civMap.getFields().get(random.nextInt(size));
        Settlement capital = new Capital(current);
        current.addSettlement(capital);
        field.getData().setSettlement(new Capital(current));
        field.getData().setOwner(current);
    }

    public Player getCurrentPlayer() {
        return players.getCurrent();
    }

    public void endTurn() {
        players.next();
        players.getCurrent().gatherResources();
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    public void init() {
        CivMapPartFactory mapPartFactory = new CivMapPartFactory();
        CivMapFactory mapFactory = new CivMapFactory(mapPartFactory);
        civMap = mapFactory.createMap(12, 6, MapStyle.HEX_HORIZONTAL);
        civMap.scale(14f);
        walker = mapPartFactory.createWalker();

        int amount = 3;
        players = new Players(amount);
        for (int i = 0; i < 3; i++) {
            setupPlayer(players.getCurrent());
            players.next();
        }

        rootController.init();
        rootController.setGuiEventListener(this);

        rootController.setMap(civMap);
        rootController.redrawMap();
    }

    @Override
    public void mouseSelect(MouseSelection selection) {
        LOGGER.debug("selection {}", selection);
    }
}
