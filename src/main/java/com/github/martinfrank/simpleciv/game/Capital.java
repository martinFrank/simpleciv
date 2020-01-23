package com.github.martinfrank.simpleciv.game;

public class Capital implements Settlement {

    private final Player owner;

    public Capital(Player owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "capital, owner:" + owner;
    }
}
