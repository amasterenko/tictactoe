package org.example;

public interface Player {
    int[] makeTurn(int[][] boardState, int playerNum, boolean repeat);

    String getName();
}
