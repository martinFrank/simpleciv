package com.github.martinfrank.simpleciv.game.advisor;

import com.github.martinfrank.simpleciv.game.Game;
import com.github.martinfrank.simpleciv.game.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class EconomicAdviser extends BaseAdviser<Advice.Economy> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EconomicAdviser.class);

    public EconomicAdviser(Game game, Player player) {
        super(game, player);
    }

    @Override
    public List<Advice<Advice.Economy>> getAdvices() {
        List<Advice<Advice.Economy>> advices = new ArrayList<>();

        //List<CivMapField> ownedFields = getGame().getOwnedFields(getPlayer());
        advices.add(Advice.economy(Advice.Priority.MEDIUM, Advice.Economy.INCOME));
        return advices;
    }

    public void execute(Advice.Economy economy) {
        LOGGER.debug("working on advice {}", economy);
    }


}
