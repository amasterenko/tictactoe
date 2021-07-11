package com.example.tictactoe.rules;

public interface Rules {
    int nextPlayer();

    boolean isNextTurnPossible();

    int getWinner();

    void reset();
}
