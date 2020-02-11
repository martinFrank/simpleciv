package com.github.martinfrank.simpleciv.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Players {

    private List<Player> allPlayers;
    private int currentPlayerIndex;

    public Players(int amountPlayer, Game game, Random random) {
        allPlayers = new ArrayList<>();
        for (int i = 0; i < amountPlayer; i++) {
            allPlayers.add(new Player(game, random));
        }
    }

    public Players(NewGameParameters parameters, Game game, Random random) {
        allPlayers = new ArrayList<>();
        for (int i = 0; i < parameters.getPlayers().size(); i++) {
            allPlayers.add(new Player(game, random));
        }
    }

    public void next() {
        currentPlayerIndex = currentPlayerIndex + 1;
        if (currentPlayerIndex >= allPlayers.size()) {
            currentPlayerIndex = 0;
        }
    }

    public Player getCurrent() {
        return allPlayers.get(currentPlayerIndex);
    }

    public int size() {
        return allPlayers.size();
    }

    public List<Player> getAllExceptCurrent() {
        return allPlayers.stream().filter(p -> !p.equals(getCurrent())).collect(Collectors.toList());
    }

    public List<Player> getAll() {
        return new ArrayList<>(allPlayers);
    }
}
