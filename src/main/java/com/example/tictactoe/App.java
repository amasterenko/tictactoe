package com.example.tictactoe;

import com.example.tictactoe.logic.GameFlow;

import java.io.*;
import java.util.Scanner;

/**
 *
 *
 */
public class App {
    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);
        PrintWriter consoleWriter = new PrintWriter(System.out, true);
        ConsoleGameSetUp setUp = new ConsoleGameSetUp(consoleScanner, consoleWriter);
        GameFlow gameFlow = new GameFlow();
        ConsoleGame game = new ConsoleGame(setUp, gameFlow, consoleScanner, consoleWriter);

        game.start();
    }
}
