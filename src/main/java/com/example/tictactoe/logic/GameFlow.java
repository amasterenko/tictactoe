package com.example.tictactoe.logic;

import com.example.tictactoe.board.Board;
import com.example.tictactoe.player.Player;

import java.util.HashMap;
import java.util.Map;
/**
 * Class represent the main logic of the game.
 *
 * @author AndrewMs
 * @version 1.0
 */
public class GameFlow implements Flow {
    private Rules rules;
    private Board board;
    private Map<Integer, Player> players = new HashMap<>(2);

    public GameFlow(Rules rules, Board board, Player pl1, Player pl2) {
        this.rules = rules;
        this.board = board;
        this.players.put(1, pl1);
        this.players.put(2, pl2);
    }

    public GameFlow() {
    }

    @Override
    public int turn() throws IllegalStateException {
        if (this.rules.isNextTurnPossible()) {
            int[] turn;
            int curPlayerNum = this.rules.nextPlayer();
            turn = this.players.get(curPlayerNum).makeTurn(this.board.getState(), curPlayerNum);
            while (!this.board.takeTurn(turn, curPlayerNum)) {
                turn = this.players.get(curPlayerNum).makeTurn(this.board.getState(), curPlayerNum);
            }
        } else {
            return rules.getWinner();
        }
        return -1;
    }

    @Override
    public void reset() {
        this.rules.reset();
    }

    @Override
    public void setRules(Rules rules) {
        this.rules = rules;
    }

    @Override
    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public void setPlayers(Map<Integer, Player> players) {
        this.players = players;
    }
}
