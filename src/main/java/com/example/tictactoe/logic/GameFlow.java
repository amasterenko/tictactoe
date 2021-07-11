package com.example.tictactoe.logic;

import com.example.tictactoe.board.Board;
import com.example.tictactoe.view.BoardView;
import com.example.tictactoe.player.Player;
import com.example.tictactoe.rules.Rules;

import java.util.HashMap;
import java.util.Map;

public class GameFlow implements Flow {
    private Rules rules;
    private Board board;
    private BoardView boardView;
    private Map<Integer, Player> players = new HashMap<>(2);

    public GameFlow(Rules rules, Board board, BoardView boardView, Player pl1, Player pl2) {
        this.rules = rules;
        this.board = board;
        this.boardView = boardView;
        players.put(1, pl1);
        players.put(2, pl2);
    }

    public GameFlow() {
    }

    @Override
    public int start() throws IllegalStateException {
        board.reset();
        rules.reset();
        boardView.show(board.getState());
        while (rules.isNextTurnPossible()) {
            int curPlayerNum = rules.nextPlayer();
            int[] turn = players.get(curPlayerNum).makeTurn(board.getState(), curPlayerNum);
            board.takeTurn(turn, curPlayerNum);
            boardView.show(board.getState());
        }
        return rules.getWinner();
    }

    public void setRules(Rules rules) {
        this.rules = rules;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setBoardView(BoardView boardView) {
        this.boardView = boardView;
    }

    public void setPlayers(Map<Integer, Player> players) {
        this.players = players;
    }
}
