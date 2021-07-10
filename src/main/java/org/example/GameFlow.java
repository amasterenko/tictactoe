package org.example;

import java.util.HashMap;
import java.util.Map;

public class GameFlow implements Flow {
    private final Rules rules;
    private final Board board;
    private final Map<Integer, Player> players = new HashMap<>(2);

    public GameFlow(Rules rules, Board board, Player pl1, Player pl2) {
        this.rules = rules;
        this.board = board;
        players.put(1, pl1);
        players.put(2, pl2);
    }

    @Override
    public int start() throws IllegalStateException {
        while (rules.isNextTurnPossible()) {
            int curPlayerNum = rules.nextPlayer();
            int[] turn = players.get(curPlayerNum).makeTurn(board.getState(), curPlayerNum, false);
            if (!board.takeTurn(turn, curPlayerNum)) {
                players.get(curPlayerNum).makeTurn(board.getState(), curPlayerNum, true);
            }
        }
        return rules.getWinner();
    }
}
