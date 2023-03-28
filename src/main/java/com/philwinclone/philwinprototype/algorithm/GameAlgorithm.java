package com.philwinclone.philwinprototype.algorithm;

import com.philwinclone.philwinprototype.Content;

import java.util.*;

public class GameAlgorithm {

    public List<Integer> generateGameTiles(int bombCount) {
        // Generated indexes
        Set<Integer> bombLocation = generateRandomIndexForBombLocation(bombCount);

        // Fill the tiles with COINS
        final List<Integer> tiles = Arrays.asList(new Integer[25]);
        Collections.fill(tiles, Content.COIN);

        // Replace the COIN with BOMB based on bombLocation
        bombLocation.forEach(i -> tiles.set(i, Content.BOMB));
        return tiles;
    }

    private Set<Integer> generateRandomIndexForBombLocation(int bombCount) {
        final Set<Integer> generatedNumbers = new HashSet<>();
        while (generatedNumbers.size() != bombCount) {
            int randNum = new Random().nextInt(0, 24);
            generatedNumbers.add(randNum);
        }
        return generatedNumbers;
    }
}
