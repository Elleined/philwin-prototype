package com.philwinclone.philwinprototype.algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ScoreMultiplierAlgorithm {
    public Long getScoreMultiplier(int selectedBombLevel, List<Integer> bombLevelList) {
        HashMap<Integer, Long> map = new HashMap<>();

        Iterator<Integer> bombLevels = bombLevelList.iterator();
        long multiplier = 1000L;
        for (int i = 0; i < 17; i++) {
            // Map each bomb level based on multiplier
            map.put(bombLevels.next(), multiplier);
            multiplier *= 2;
        }
        // Select the multiplier based on selectedBombLevel
        return map.get( selectedBombLevel );
    }
}
