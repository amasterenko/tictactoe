package org.example.rules;

public interface Rules {
    int nextPlayer();

    boolean isNextTurnPossible();

    int getWinner();

    void reset();
}
