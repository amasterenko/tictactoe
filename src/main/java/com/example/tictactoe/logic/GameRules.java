package com.example.tictactoe.logic;

import com.example.tictactoe.board.Board;
/**
 * Class represent the rules of the game.
 *
 * @author AndrewMs
 * @version 1.0
 */
public class GameRules implements Rules {
    private final Board board;
    private int lastPlayer = 2;
    private int winPlayer;

    public GameRules(Board board) {
        this.board = board;
    }

    /**
     * Defines a player making the next turn.
     * Player1 always makes the first turn.
     * @return Optional with next player if next turn possible
     */
    @Override
    public int nextPlayer() {
        if (!isNextTurnPossible()) {
            return 0;
        }
        lastPlayer = lastPlayer == 1 ? 2 : 1;
        return lastPlayer;
    }

    /**
     * Defines the next turn possibility.
     * @return True if the board is not full and there is no winner of the game.
     */
    @Override
    public boolean isNextTurnPossible() {
        getWinner();
        return winPlayer == 0 && !board.isCompleted();
    }

    /**
     * Defines if the game has a winner.
     * @return Optional with a player if the winner exists.
     */
    @Override
    public int getWinner() {
        if (winPlayer == 0 && board.hasCompletedLine()) {
            winPlayer = lastPlayer;
        }
        return winPlayer;
    }

    /**
     * Resets the values for new game.
     *
     */
    @Override
    public void reset() {
        this.lastPlayer = 2;
        this.winPlayer = 0;
        this.board.reset();
    }
}
