package org.example;

import java.io.PrintWriter;
import java.util.Scanner;

public class ConsolePlayer implements Player {
    private final String name;
    private final PrintWriter out;
    private final Scanner scanner;
    private final BoardView boardView;

    public ConsolePlayer(String name, Scanner scanner, PrintWriter out, BoardView boardView) {
        this.name = name;
        this.out = out;
        this.scanner = scanner;
        this.boardView = boardView;
    }

    @Override
    public int[] makeTurn(int[][] boardState, int curPlayerNum, boolean repeat) throws IllegalStateException {
        int x;
        int y;
        boolean goOn = true;
        boardView.show(boardState);
        out.println((name + " turns:"));
        if (repeat) {
            out.println(("Incorrect turn! Try again? (y/n)"));
            goOn = "y".equalsIgnoreCase(scanner.next());
        }
        while (goOn) {
            try {
                out.println(("Input x:"));
                x = Integer.parseInt(scanner.next());
                out.println(("Input y:"));
                y = Integer.parseInt(scanner.next());
                if (x <= 0 || x > boardState.length || y <= 0 || y > boardState.length) {
                    throw new IllegalArgumentException("Turn is not valid");
                } else {
                    return new int[]{x, y};
                }
            } catch (IllegalArgumentException e) {
                out.println(("Incorrect turn! Try again? (y/n)"));
                goOn = "y".equalsIgnoreCase(scanner.next());
            }
        }
        throw new IllegalStateException("The turn was canceled");
    }

    @Override
    public String getName() {
        return name;
    }
}
