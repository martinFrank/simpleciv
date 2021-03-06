package com.github.martinfrank.simpleciv.map;

import com.github.martinfrank.maplib.Map;
import com.github.martinfrank.maplib.MapStyle;
import com.github.martinfrank.simpleciv.game.Player;
import com.github.martinfrank.simpleciv.mapdata.CivMapData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CivMap extends Map<CivMapData, CivMapField, CivMapEdge, CivMapNode, CivMapWalker> {


    public CivMap(int width, int height, MapStyle style, CivMapData mapData) {
        super(width, height, style, mapData);
    }

//    @Override
//    public void draw(Object drawContext) {
//        getFields().forEach(f -> f.draw(drawContext));
//    }


    public CivMapField getRandomField(Random random) {
        int size = getFields().size();
        return getFields().get(random.nextInt(size));
    }

    public List<CivMapField> getFields(CivMapField center, int radius) {
        List<CivMapField> inside = new ArrayList<>(Collections.singletonList(center));
        List<CivMapField> fields = new ArrayList<>();
        for (int i = 0; i < radius; i++) {
            fields.clear();
            for (CivMapField in : inside) {
                for (CivMapField nbg : in.getFields()) {
                    if (!inside.contains(nbg) && !fields.contains(nbg)) {
                        fields.add(nbg);
                    }
                }
            }
            inside.addAll(fields);
        }
        return fields;
    }

    public CivMapField randomWithMinimumDistance(int distance, Player current, Random random) {
        while (true) {
            CivMapField field = getRandomField(random);
            boolean hasFailed = false;
            for (int r = 0; r < distance; r++) {
                List<CivMapField> inRadius = getFields(field, r + 1);
                if (r == 0 && inRadius.size() != 6) {
                    hasFailed = true;
                    break;
                }
                if (r == 1 && inRadius.size() != 12) {
                    hasFailed = true;
                    break;
                }
                for (CivMapField fieldInRadius : inRadius) {
                    if (fieldInRadius.getSettlement() != null && !fieldInRadius.getSettlement().isOwnedBy(current)) {
                        hasFailed = true;
                        break;
                    }
                }
                if (hasFailed) {
                    break;
                }
            }
            if (hasFailed) {
                continue;
            }
            return field;
        }
    }
}
