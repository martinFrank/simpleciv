package com.github.martinfrank.simpleciv.game;

import java.util.HashMap;
import java.util.Map;

public class CultureMap {

    private Map<Player, Double> internalRepresentation = new HashMap<>();

    public void addCulture(double addendum, Player player) {
        double newValue = addendum;
        if (internalRepresentation.containsKey(player)) {
            newValue = internalRepresentation.get(player) + addendum;
        }
        internalRepresentation.put(player, newValue);
    }

    public void clear() {
        internalRepresentation.clear();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        internalRepresentation.forEach((key, value) -> sb.append("player:").append(key).append(" culture:").append(value));
        return sb.toString();
    }

    public Player getmostCultivate() {
        Player player = null;
        double culture = 0;
        for (Map.Entry<Player, Double> entry : internalRepresentation.entrySet()) {
            if (entry.getValue() > culture) {
                culture = entry.getValue();
                player = entry.getKey();
            }
        }
        return player;
    }
}
