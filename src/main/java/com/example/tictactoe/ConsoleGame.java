package com.example.tictactoe;

import com.example.tictactoe.logic.GameFlow;
import com.example.tictactoe.player.Player;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

public class ConsoleGame {
    private final ConsoleGameSetUp setUp;
    private final GameFlow gameFlow;
    private final Scanner in;
    private final PrintWriter out;

    public ConsoleGame(ConsoleGameSetUp setUp, GameFlow gameFlow, Scanner in, PrintWriter out) {
        this.setUp = setUp;
        this.gameFlow = gameFlow;
        this.in = in;
        this.out = out;
    }

    public void start() {
        out.println("---X/O GAME---");
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
                if (!setUp.init()) {
                    continue;
                }

                while (goOn) {
                    Map<Integer, Player> players = Map.of(
                            1, setUp.getPlayer1(), 2, setUp.getPlayer2()
                    );
                    this.gameFlow.setRules(setUp.getRules());
                    this.gameFlow.setBoard(setUp.getBoard());
                    this.gameFlow.setBoardView(setUp.getBoardView());
                    this.gameFlow.setPlayers(Map.of(1, setUp.getPlayer1(), 2, setUp.getPlayer2()));
                    int gameResult;
                    try {
                        gameResult = this.gameFlow.start();
                    } catch (IllegalStateException e) {
                        break;
                    }
                    if (gameResult == 0) {
                        this.setUp.getDrawView().show();
                    } else {
                        this.setUp.getWinView().show(players.get(gameResult));
                    }
                    out.println("Next round? (y/n)");
                    if (!in.next().equalsIgnoreCase("y")) {
                        break;
                    }
                }
            }
        }
    }
}
