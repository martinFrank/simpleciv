package com.github.martinfrank.simpleciv.game.advisor;

import com.github.martinfrank.simpleciv.game.Game;
import com.github.martinfrank.simpleciv.game.Player;

public abstract class BaseAdviser<T> implements Adviser<T> {


    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    private final Game game;
    private final Player player;

    public BaseAdviser(Game game, Player player) {
        this.game = game;
        this.player = player;
    }


}
