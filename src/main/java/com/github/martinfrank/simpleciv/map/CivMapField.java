package com.github.martinfrank.simpleciv.map;

import com.github.martinfrank.maplib.MapField;
import com.github.martinfrank.simpleciv.game.Player;
import com.github.martinfrank.simpleciv.game.Settlement;
import com.github.martinfrank.simpleciv.game.Unit;
import com.github.martinfrank.simpleciv.mapdata.CivMapFieldData;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CivMapField extends MapField<CivMapFieldData, CivMapField, CivMapEdge, CivMapNode> {

    public CivMapField(CivMapFieldData mapFieldData) {
        super(mapFieldData);
    }

    @Override
    public String toString() {
        return getData().toString();
    }

    public boolean isOwnedBy(Player player) {
        return getData() != null && getData().getOwner() != null && getData().getOwner().equals(player);
    }

    public List<Unit> getUnits(Player player) {
        if (getData() != null && getData().getUnits() != null) {
            return getData().getUnits().stream().filter(u -> u.getOwner().equals(player)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public Settlement getSettlement() {
        if (getData() != null && getData().getSettlement() != null) {
            return getData().getSettlement();
        }
        return null;
    }

    public void addUnit(Unit unit) {
        getData().getUnits().add(unit);
    }
}
