package com.github.martinfrank.simpleciv.game;

import com.github.martinfrank.simpleciv.game.advisor.Advisers;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {

    private final Random random = new Random();
    private final String name;
    private final Color color;
    private final List<Settlement> settlements = new ArrayList<>();
    private final Advisers advisors;
    private final Game game;

    public Player(Game game) {
        this.game = game;
        int length = 4 + random.nextInt(4);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            char c = (char) (65 + random.nextInt(23));
            sb.append(c);
        }
        name = sb.toString();
        color = new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1);
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

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return name;
    }

    public void addSettlement(Settlement settlement) {
        settlements.add(settlement);
    }
}
