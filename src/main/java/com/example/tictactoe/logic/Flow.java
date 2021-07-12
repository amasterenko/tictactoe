package com.example.tictactoe.logic;

import com.example.tictactoe.board.Board;
import com.example.tictactoe.player.Player;

import java.util.Map;

public interface Flow {
    int turn() throws IllegalStateException;

    void reset();

    void setRules(Rules rules);

    void setBoard(Board board);

    void setPlayers(Map<Integer, Player> players);
}
