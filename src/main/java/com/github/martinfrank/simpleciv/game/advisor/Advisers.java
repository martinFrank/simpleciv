package com.github.martinfrank.simpleciv.game.advisor;

import com.github.martinfrank.simpleciv.game.Game;
import com.github.martinfrank.simpleciv.game.Player;

import java.util.ArrayList;
import java.util.List;

public class Advisers {

    private final MilitaryAdviser militaryAdviser;
    private final EconomicAdviser economicAdviser;

    private final Player player;
    private final Game game;


    public Advisers(Game game, Player player) {
        this.game = game;
        this.player = player;
        militaryAdviser = new MilitaryAdviser(game, player);
        economicAdviser = new EconomicAdviser(game, player);
    }

    public void executeStartAdvices() {
        List<Advice> advices = new ArrayList<>();
        advices.addAll(militaryAdviser.getAdvices());
        advices.addAll(economicAdviser.getAdvices());

        //FIXME loop über alle 
        Advice advice = pollMostUrgent(advices);
        executeAdvice(advice);
    }

    private void executeAdvice(Advice advice) {
        Object task = advice.getAdvice();
        if (task instanceof Advice.Economy) {
            Advice.Economy economy = (Advice.Economy) task;
            economicAdviser.execute(economy);
        }
        if (task instanceof Advice.Military) {
            Advice.Military military = (Advice.Military) task;
            militaryAdviser.execute(military);
        }
    }


    private static Advice pollMostUrgent(List<Advice> advices) {
        for (Advice advice : advices) {
            if (advice.getPriority() == Advice.Priority.EMERGENCY) {
                return advice;
            }
        }
        for (Advice advice : advices) {
            if (advice.getPriority() == Advice.Priority.HIGH) {
                return advice;
            }
        }
        for (Advice advice : advices) {
            if (advice.getPriority() == Advice.Priority.MEDIUM) {
                return advice;
            }
        }
        for (Advice advice : advices) {
            if (advice.getPriority() == Advice.Priority.LOW) {
                return advice;
            }
        }
        return null;
    }
}
