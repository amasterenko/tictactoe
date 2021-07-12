package com.example.tictactoe.player;

import com.example.tictactoe.consolegame.input.ValidateInput;

import java.io.PrintWriter;
import java.util.NoSuchElementException;

public class ConsolePlayer implements Player {
    private final String name;
    private final PrintWriter out;
    private final ValidateInput<Integer> inputInt;

    public ConsolePlayer(String name, ValidateInput<Integer> inputInt, PrintWriter out) {
        this.name = name;
        this.out = out;
        this.inputInt = inputInt;
    }

    /**
     * Asks the user to input the coordinates of the turn.
     * @param boardState state of the game board as a matrix with players numbers or zeros.
     * @param curPlayerNum a number (1 or 2) that represents the current player in the board state.
     * @return the turn coordinates int[2], where X goes first and O goes second.
     */
    @Override
    public int[] makeTurn(int[][] boardState, int curPlayerNum) throws IllegalStateException {
        int x;
        int y;
        out.println((name + " turns:"));
        try {
            x = inputInt.ask("Input x:", i -> i >= 1 && i <= boardState.length).get();
            y = inputInt.ask("Input y:", i -> i >= 1 && i <= boardState.length).get();
            return new int[]{x, y};
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("The turn was canceled");
        }
    }

    @Override
    public String getName() {
        return this.name;
    }
}
