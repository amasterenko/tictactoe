package com.example.tictactoe.logic;

public interface Rules {
    int nextPlayer();

    boolean isNextTurnPossible();

    int getWinner();

    void reset();
}
