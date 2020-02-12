package com.github.martinfrank.simpleciv.game;

import com.github.martinfrank.simpleciv.game.advisor.Advisers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {

//    private final Random random;
private final String name;
    private final int color;
    private final List<Settlement> settlements = new ArrayList<>();
    private final List<Unit> units = new ArrayList<>();
    private final Advisers advisors;

    public Player(Game game, Random random) {
//        this.random = random;
        int length = 4 + random.nextInt(4);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = (char) (65 + random.nextInt(23));
            sb.append(c);
        }
        name = sb.toString();
        color = random.nextInt();
        advisors = new Advisers(game, this);
    }


    public void playTurn() {
        advisors.executeStartAdvices();
    }

    //FIXME maybe better name
    public void gatherResources() {
        for (Settlement settlement : settlements) {
            settlement.addProductivity();
        }
    }

    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        return name;
    }

    public void addSettlement(Settlement settlement) {
        settlements.add(settlement);
    }

    public List<Unit> getUnits() {
        return units;
    }
}
