package com.github.martinfrank.simpleciv.game;

import com.github.martinfrank.maplib.MapStyle;
import com.github.martinfrank.simpleciv.gui.GuiEventListener;
import com.github.martinfrank.simpleciv.gui.MouseSelection;
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

    public Game(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public void endTurn() {
        players.next();
        LOGGER.debug("now its turn of player {}", players.getCurrent());
        //start of next players turn
        players.getCurrent().gatherResources();
        players.getCurrent().playTurn();
    }

    public void init() {
        startNewGame(new NewGameParameters());
    }

    private void startNewGame(NewGameParameters parameters) {
        createMap();
        createPlayers(parameters);
        addPlayersToMap();
    }

    private void addPlayersToMap() {
        for (int i = 0; i < players.size(); i++) {
            Player current = players.getCurrent();
            CivMapField field = civMap.randomWithMinimumDistance(4, current, random);
            Settlement capital = new Settlement(current, field);
            if (i == 0) {
                capital.culture = 123;
            }
            current.addSettlement(capital);
            field.getData().setSettlement(capital);
            field.getData().setOwner(current);
            players.next();
        }
        applyCulture();
    }

    //wirkung von Kultur auf die Karte
    public void applyCulture() {
        civMap.getFields().forEach(f -> f.getData().clearCulture());//clear all culture
        for (Player player : players.getAll()) {
            for (Settlement settlement : getSettlements(player)) {
                applyCultureOfSettlement(settlement, player);
            }
        }
        civMap.getFields().forEach(f -> f.getData().setOwnerByCulture());//clear all culture
    }

    private void applyCultureOfSettlement(Settlement settlement, Player player) {
        double culture = settlement.getCulture();
        int radius = (int) Math.log10(culture);
        CivMapField center = settlement.getField();
        for (int r = 1; r <= radius; r++) {
            double distanceFactor = 10d * Math.pow(10, (-1 * r));
            double effectiveCulture = culture * distanceFactor;
            List<CivMapField> circle = civMap.getFields(center, r);
            circle.forEach(f -> f.getData().getCultureMap().addCulture(effectiveCulture, player));
        }
        // radius 1 == settlement
        //1 * culture for radius 2
        //0.1 * culture for radius 3
        //0.01 * culture for radius 4
    }

    private void createPlayers(NewGameParameters parameters) {
        players = new Players(parameters, this, random);
    }

    private void createMap() {
        CivMapPartFactory mapPartFactory = new CivMapPartFactory();
        CivMapFactory mapFactory = new CivMapFactory(mapPartFactory);
        civMap = mapFactory.createMap(32, 16, MapStyle.HEX_HORIZONTAL);
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

    public CivMap getMap() {
        return civMap;
    }
}
