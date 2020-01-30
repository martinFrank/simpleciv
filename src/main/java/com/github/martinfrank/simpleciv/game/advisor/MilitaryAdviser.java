package com.github.martinfrank.simpleciv.game.advisor;

import com.github.martinfrank.simpleciv.game.Game;
import com.github.martinfrank.simpleciv.game.Player;
import com.github.martinfrank.simpleciv.game.unit.Unit;
import com.github.martinfrank.simpleciv.map.CivMapField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MilitaryAdviser extends BaseAdviser<Advice.Military> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MilitaryAdviser.class);

    public MilitaryAdviser(Game game, Player player) {
        super(game, player);
    }

    @Override
    public List<Advice<Advice.Military>> getAdvices() {
        List<Unit> ownedUnits = getGame().getAllUnits(getPlayer());
        List<CivMapField> ownedFields = getGame().getOwnedFields(getPlayer());

        List<Advice<Advice.Military>> advices = new ArrayList<>();

        if (ownedUnits.size() < 1) {
            advices.add(Advice.militaryAdvice(Advice.Priority.EMERGENCY, Advice.Military.MELEE));
        }

        return advices;
    }

    public void execute(Advice.Military military) {
        LOGGER.debug("working on advice {}", military);
    }

}
