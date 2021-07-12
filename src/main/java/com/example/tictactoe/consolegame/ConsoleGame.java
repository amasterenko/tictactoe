package com.example.tictactoe.consolegame;

import com.example.tictactoe.consolegame.init.ConsoleGameInit;
import com.example.tictactoe.consolegame.input.ConsoleIntValidateInput;
import com.example.tictactoe.consolegame.input.ValidateInput;
import com.example.tictactoe.logic.GameFlow;

import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
/**
 * Class represent console game.
 *
 * @author AndrewMs
 * @version 1.0
 */
public class ConsoleGame {
    private final static int MIN_BOARD_SIZE = 2;
    private final static int MAX_BOARD_SIZE = 20;
    private final GameFlow gameFlow;
    private GameRounds rounds;
    private final Scanner in;
    private final PrintWriter out;
    private final ValidateInput<Integer> inputInt;
    private final List<ConsoleGameInit> initializers;

    public ConsoleGame(List<ConsoleGameInit> inits, GameFlow gameFlow, GameRounds rounds,
                        Scanner in, PrintWriter out) {
        this.initializers = inits;
        this.gameFlow = gameFlow;
        this.rounds = rounds;
        this.in = in;
        this.out = out;
        this.inputInt = new ConsoleIntValidateInput(in, out);
    }

    public void start() {
        out.println("---X/O GAME---");
        boolean goOn = true;
        while (goOn) {
            int count = 1;
            for (ConsoleGameInit init : initializers) {
                out.println(count++ + ". " + init.getGameType().getName());
            }
            out.println(count + ". " + "Exit");
            var answer = inputInt.ask("Select option:", o -> o >= 1 && o <= 4);
            if (answer.isEmpty() || answer.get() == 4) {
                break;
            }
            var setUp = initializers.get(answer.get() - 1);
            if (!setUp.init(MIN_BOARD_SIZE, MAX_BOARD_SIZE)) {
                continue;
            }
            this.rounds.play(setUp, this.gameFlow);
        }
    }
}
