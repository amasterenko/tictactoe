package com.example.tictactoe.board;

public interface Board {
    boolean takeTurn(int[] coordinates, int playerNum);

    boolean isCompleted();

    boolean hasCompletedLine();

    int[][] getState();

    void reset();
}
