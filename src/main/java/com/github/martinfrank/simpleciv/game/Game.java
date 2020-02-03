package com.github.martinfrank.simpleciv.game;

import com.github.martinfrank.maplib.MapStyle;
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
            CivMapField field;
            if (i == 0) {
                field = civMap.getRandomField(random);
            } else {
                field = randomWithMinimumDistance(4);
            }
            Settlement capital = new Settlement(current, field);
            current.addSettlement(capital);
            field.getData().setSettlement(capital);
            field.getData().setOwner(current);
            //settting the area around a city -> Should be done via culture
            field.getFields().forEach(f -> f.getData().setOwner(current));
            players.next();
        }
    }

    private CivMapField randomWithMinimumDistance(int distance) {
        while (true) {
            CivMapField field = civMap.getRandomField(random);
            if (field.getFields().size() != 6) {
                continue;
            }
            for (Player p : players.getAllExceptCurrent()) {
                if (!getSettlements(p).isEmpty()) {
                    Settlement settlement = getSettlements(p).get(0);
                    List<CivMapField> path = civMap.aStar(settlement.getField(), field, walker, 100);
                    if (path.size() >= distance) {
                        return field;
                    }
                }
            }
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
        civMap = mapFactory.createMap(20, 12, MapStyle.HEX_HORIZONTAL);
        civMap.scale(6f);
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

    public List<Settlement> getSettlements(Player player) {
        List<Settlement> settlements = new ArrayList<>();
        civMap.getFields().stream().filter(f -> f.getSettlement() != null && f.getSettlement().isOwnedBy(player)).forEach(f -> settlements.add(f.getSettlement()));
        return settlements;
    }
}
