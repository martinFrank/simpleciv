package com.github.martinfrank.simpleciv.game;

import com.github.martinfrank.maplib.MapStyle;
import com.github.martinfrank.simpleciv.game.unit.Unit;
import com.github.martinfrank.simpleciv.gui.GuiEventListener;
import com.github.martinfrank.simpleciv.gui.MouseSelection;
import com.github.martinfrank.simpleciv.gui.RootController;
import com.github.martinfrank.simpleciv.map.*;
import com.github.martinfrank.simpleciv.res.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Game implements GuiEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

    private CivMap civMap;
    private Players players;
    private CivMapWalker walker;
    private final Random random = new Random();
    private final ResourceManager resourceManager;
    private RootController rootController;

    public Game(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }


    public Player getCurrentPlayer() {
        return players.getCurrent();
    }

    public void endTurn() {
        players.next();
        LOGGER.debug("now its turn of player {}", players.getCurrent());
        players.getCurrent().gatherResources();
        players.getCurrent().playTurn();
    }

    public void setRootController(RootController rootController) {
        this.rootController = rootController;
    }

    public void init() {
        createMap();
        createPlayers();
        addPlayersToMap();
        createGui();
    }

    private void addPlayersToMap() {
        for (int i = 0; i < players.size(); i++) {
            Player current = players.getCurrent();
            int size = civMap.getFields().size();
            CivMapField field = civMap.getFields().get(random.nextInt(size));
            Settlement capital = new Capital(current);
            current.addSettlement(capital);
            field.getData().setSettlement(new Capital(current));
            field.getData().setOwner(current);
            players.next();
        }
    }

    private void createGui() {
        rootController.init();
        rootController.setGuiEventListener(this);
        rootController.setMap(civMap);
        rootController.redrawMap();
    }

    private void createPlayers() {
        players = new Players(3, this);
    }

    private void createMap() {
        CivMapPartFactory mapPartFactory = new CivMapPartFactory();
        CivMapFactory mapFactory = new CivMapFactory(mapPartFactory);
        civMap = mapFactory.createMap(12, 6, MapStyle.HEX_HORIZONTAL);
        civMap.scale(14f);
        walker = mapPartFactory.createWalker();
    }

    @Override
    public void mouseSelect(MouseSelection selection) {
        LOGGER.debug("selection {}", selection);
    }

    public List<CivMapField> getOwnedFields(Player player) {
        return civMap.getFields().stream().filter(f -> f.isOwnedBy(player)).collect(Collectors.toList());
    }

    public List<Unit> getAllUnits(Player player) {
        List<Unit> allUnits = new ArrayList<>();
        civMap.getFields().forEach(f -> allUnits.addAll(f.getUnits(player)));
        return allUnits;
    }
}
