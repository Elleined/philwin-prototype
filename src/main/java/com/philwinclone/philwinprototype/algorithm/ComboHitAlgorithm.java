package com.philwinclone.philwinprototype.algorithm;

import java.util.*;

public class ComboHitAlgorithm {
    public Long getComboScore(int combo, long score) {
        HashMap<Integer, Long> map = new HashMap<>();

        Iterator<Float> comboAlgorithm = comboAlgorithm().iterator();
        Iterator<Integer> possibleComboHit = possibleComboHitList().iterator();
        while (possibleComboHit.hasNext() && comboAlgorithm.hasNext()) {
            // comboAlgorithm provides the percent that will be added to the score
            long percent = Math.round(score * (comboAlgorithm.next() / 100.0f));
            map.put(possibleComboHit.next(), percent);
        }
        map.forEach((t, s) -> System.out.println(t + " " + s));
        return map.get( combo );
    }

    private List<Float> comboAlgorithm() {
        List<Float> comboPercentList = new ArrayList<>();
        final int maxComboHit = possibleComboHitList().size();
        float startingComboPercent = 5f;
        for (int i = 0; i < maxComboHit; i++) {
            comboPercentList.add( startingComboPercent );
            startingComboPercent += 2; // here 2 represent how many percent you want to add every combo hit
        }
        return comboPercentList;
    }

    private List<Integer> possibleComboHitList() {
        return Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21);
    }
}
