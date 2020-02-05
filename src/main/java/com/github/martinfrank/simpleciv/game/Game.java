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
            CivMapField field = randomWithMinimumDistance(4, current);
            Settlement capital = new Settlement(current, field);
            if (i == 0) {
                capital.culture = 11;
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
        LOGGER.debug("radius:{}", radius);
        for (int r = 1; r <= radius; r++) {
            double distanceFactor = 10d * Math.pow(10, (-1 * r));
            LOGGER.debug("distanceFactor:{}", distanceFactor);
            double effectiveCulture = culture * distanceFactor;
            LOGGER.debug("effectiveCulture:{}", effectiveCulture);
            List<CivMapField> circle = civMap.getFields(center, r);
            circle.forEach(f -> f.getData().getCultureMap().addCulture(effectiveCulture, player));
        }

        //1 * culture for radius 1
        //0.1 * culture for radius 2
        //0.01 * culture for radius 3
    }

    private CivMapField randomWithMinimumDistance(int distance, Player current) {
        while (true) {
            CivMapField field = civMap.getRandomField(random);
            boolean hasFailed = false;
            for (int r = 0; r < distance; r++) {
                List<CivMapField> inRadius = civMap.getFields(field, r + 1);
                if (r == 0 && inRadius.size() != 6) {
                    hasFailed = true;
                    break;
                }
                if (r == 1 && inRadius.size() != 12) {
                    hasFailed = true;
                    break;
                }
                for (CivMapField fieldInRadius : inRadius) {
                    if (fieldInRadius.getSettlement() != null && !fieldInRadius.getSettlement().isOwnedBy(current)) {
                        hasFailed = true;
                        break;
                    }
                }
                if (hasFailed) {
                    break;
                }
            }
            if (hasFailed) {
                continue;
            }
            return field;
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
}
