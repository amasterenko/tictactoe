package org.example;

public interface Rules {
    int nextPlayer();

    boolean isNextTurnPossible();

    int getWinner();

    void resetPlayers();
}
