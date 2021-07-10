package org.example;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

public class ConsoleGame {
    private final Scanner in;
    private final PrintWriter out;

    public ConsoleGame(Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    public void start() {
        out.println("***TIC-TAC-TOE GAME***");
        boolean goOn = true;
        while (goOn) {
            out.println("1. New Game");
            out.println("2. Exit");
            out.println("Select option:");
            String answer = in.next();
            if (answer.equals("2")) {
                break;
            }
            if (answer.equals("1")) {
                ConsoleGameSetUp setUp = new ConsoleGameSetUp(in, out);
                if (!setUp.init()) {
                    break;
                }
                Board board = setUp.getBoard();
                Rules rules = setUp.getRules();
                while (goOn) {
                    Map<Integer, Player> players = Map.of(1, setUp.getPlayer1(), 2, setUp.getPlayer2());
                    GameFlow game = new GameFlow(rules, board, setUp.getPlayer1(), setUp.getPlayer2());
                    int res;
                    try {
                        res = game.start();
                    } catch (IllegalStateException e) {
                        break;
                    }
                    if (res == 0) {
                        out.println("****");
                        out.println("Draw.");
                        out.println("****");
                    } else {
                        String str = players.get(res).getName() + " won!";
                        out.println("*".repeat(str.length()));
                        out.println(str);
                        out.println("*".repeat(str.length()));
                    }
                    out.println("Next round? (y/n)");
                    if (in.next().equalsIgnoreCase("y")) {
                        board.reset();
                        rules.resetPlayers();
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
