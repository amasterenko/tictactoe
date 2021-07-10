package org.example;

public class GameRules implements Rules {
    private final Board board;
    private int lastPlayer;
    private int winPlayer;

    public GameRules(Board board) {
        this.board = board;
    }

    /**
     * Define a player that will turn next.
     * The right of the first turn is defined randomly.
     * @return Optional with next player if next turn possible
     */
    @Override
    public int nextPlayer() {
        if (!isNextTurnPossible()) {
            return 0;
        }
        if (lastPlayer == 0) {
            lastPlayer = Math.round(Math.random()) == 1 ? 1 : 2;
        } else {
            lastPlayer = lastPlayer == 1 ? 2 : 1;
        }
        return lastPlayer;
    }

    /**
     * Define the next turn possibility.
     * @return True if the board is not full and there is no winner of the game
     */
    @Override
    public boolean isNextTurnPossible() {
        getWinner();
        return winPlayer == 0 && !board.isCompleted();
    }

    /**
     * Defines if the game has a winner.
     * @return Optional with a player if the winner exists
     */
    @Override
    public int getWinner() {
        if (winPlayer == 0 && board.hasCompletedLine()) {
            winPlayer = lastPlayer;
        }
        return winPlayer;
    }

    @Override
    public void resetPlayers() {
        this.lastPlayer = 0;
        this.winPlayer = 0;
    }
}
