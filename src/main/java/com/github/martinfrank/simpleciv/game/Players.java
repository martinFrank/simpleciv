package com.github.martinfrank.simpleciv.game;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private List<Player> players;
    private int currentPlayerIndex;

    public Players(int amountPlayer, Game game) {
        players = new ArrayList<>();
        for (int i = 0; i < amountPlayer; i++) {
            players.add(new Player(game));
        }
    }

    public void next() {
        currentPlayerIndex = currentPlayerIndex + 1;
        if (currentPlayerIndex >= players.size()) {
            currentPlayerIndex = 0;
        }
    }

    public Player getCurrent() {
        return players.get(currentPlayerIndex);
    }

    public int size() {
        return players.size();
    }
}
