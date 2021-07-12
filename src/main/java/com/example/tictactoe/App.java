package com.example.tictactoe;

import com.example.tictactoe.consolegame.*;
import com.example.tictactoe.consolegame.init.ConsoleGameInit;
import com.example.tictactoe.consolegame.init.ConsoleGameInitCC;
import com.example.tictactoe.consolegame.init.ConsoleGameInitHC;
import com.example.tictactoe.consolegame.init.ConsoleGameInitHH;
import com.example.tictactoe.logic.GameFlow;

import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 * Class represents the main app.
 *
 * @author AndrewMs
 * @version 1.0
 */
public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out, true);
        GameFlow gameFlow = new GameFlow();
        ConsoleGameInit initHH = new ConsoleGameInitHH(in, out);
        ConsoleGameInit initHC = new ConsoleGameInitHC(in, out);
        ConsoleGameInit initCC = new ConsoleGameInitCC(in, out);
        GameRounds rounds = new ConsoleGameRounds(in, out);
        ConsoleGame game = new ConsoleGame(List.of(initHH, initHC, initCC),
                gameFlow, rounds, in, out);

        game.start();
    }
}
