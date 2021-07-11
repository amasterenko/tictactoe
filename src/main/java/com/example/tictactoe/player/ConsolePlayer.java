package com.example.tictactoe.player;

import java.io.PrintWriter;
import java.util.Scanner;

public class ConsolePlayer implements Player {
    private final String name;
    private final PrintWriter out;
    private final Scanner scanner;

    public ConsolePlayer(String name, Scanner scanner, PrintWriter out) {
        this.name = name;
        this.out = out;
        this.scanner = scanner;
    }

    @Override
    public int[] makeTurn(int[][] boardState, int curPlayerNum) throws IllegalStateException {
        int x;
        int y;
        out.println((name + " turns:"));
        boolean goOn = true;
        while (goOn) {
            try {
                out.println(("Input x:"));
                x = Integer.parseInt(scanner.next());
                out.println(("Input y:"));
                y = Integer.parseInt(scanner.next());
                if (x < 1 || x > boardState.length || y < 1
                        || y > boardState.length || boardState[y - 1][x - 1] != 0
                ) {
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
