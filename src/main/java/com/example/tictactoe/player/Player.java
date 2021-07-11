package com.example.tictactoe.player;

public interface Player {
    int[] makeTurn(int[][] boardState, int playerNum) throws IllegalStateException;

    String getName();
}
