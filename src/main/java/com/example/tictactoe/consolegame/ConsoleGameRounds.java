package com.example.tictactoe.consolegame;

import com.example.tictactoe.consolegame.init.ConsoleGameInit;
import com.example.tictactoe.consolegame.input.ConsoleStrValidateInput;
import com.example.tictactoe.consolegame.input.ValidateInput;
import com.example.tictactoe.logic.GameFlow;
import com.example.tictactoe.player.Player;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
/**
 * Class plays the game rounds with the same settings.
 *
 * @author AndrewMs
 * @version 1.0
 */
public class ConsoleGameRounds implements GameRounds {
    private final Scanner in;
    private final PrintWriter out;
    private final ValidateInput<String> inputStr;

    public ConsoleGameRounds(Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        this.inputStr = new ConsoleStrValidateInput(in, out);
    }

    @Override
    public void play(ConsoleGameInit setUp, GameFlow gameFlow) {
        Map<Integer, Player> players = Map.of(
                1, setUp.getPlayer1(), 2, setUp.getPlayer2()
        );
        gameFlow.setRules(setUp.getRules());
        gameFlow.setBoard(setUp.getBoard());
        gameFlow.setPlayers(Map.of(1, setUp.getPlayer1(), 2, setUp.getPlayer2()));

        while (true) {
            int gameResult = -1;
            gameFlow.reset();
            while (gameResult == -1) {
                try {
                    setUp.getBoardView().show(setUp.getBoard().getState());
                    gameResult = gameFlow.turn();
                } catch (IllegalStateException e) {
                    break;
                }
            }
            if (gameResult == 0) {
                setUp.getDrawView().show();
            }
            if (gameResult > 0) {
                setUp.getWinView().show(players.get(gameResult));
            }
            String answer = inputStr.ask("Next round? (y/n)",
                    s -> "y".equalsIgnoreCase(s) || "n".equalsIgnoreCase(s)).get();
            if (answer.equalsIgnoreCase("n")) {
                break;
            }
        }
    }
}
