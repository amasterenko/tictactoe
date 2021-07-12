package com.example.tictactoe.player;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
/**
 * Class represent a dummy AI that makes turns randomly.
 *
 * @author AndrewMs
 * @version 1.0
 */
public class ComputerPlayer implements Player {
    private final String name;
    private final PrintWriter out;

    public ComputerPlayer(String name, PrintWriter out) {
        this.out = out;
        this.name = name;
    }

    /**
     * Makes turn.
     * @param boardState state of the game board as a matrix of cells.
     * @param curPlayerNum a number (1 or 2) that represents the current player in the board state.
     * @return the turn coordinates int[2], where X goes first and O goes second.
     */
    @Override
    public int[] makeTurn(int[][] boardState, int curPlayerNum) {
        out.println((name + " turns:"));
        Map<Integer, Map<Integer, Integer>> cells = new HashMap<>();
        int count = 0;
        for (int i = 0; i < boardState.length; i++) { //y
            for (int j = 0; j < boardState.length; j++) { //x
                if (boardState[i][j] == 0) {
                    cells.put(++count, Map.of(j, i)); //x,y
                }
            }
        }
        int[] res = new int[2];
        int indx = (int) ((Math.random() * (count - 1)) + 1);
        cells.get(indx).forEach((key, value) -> {
            res[0] = key + 1;
            res[1] = value + 1;
        });
        return res;
    }

    @Override
    public String getName() {
        return name;
    }
}
