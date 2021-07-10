package org.example;

import java.io.*;
import java.util.Scanner;

/**
 *
 *
 */
public class App {
    public static void main(String[] args) throws IOException {
        ConsoleGame game = new ConsoleGame(new Scanner(System.in), new PrintWriter(System.out, true));
        game.start();
    }
}
