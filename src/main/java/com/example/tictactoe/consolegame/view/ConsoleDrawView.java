package com.example.tictactoe.consolegame.view;

import java.io.PrintWriter;
/**
 * Class displays the draw.
 *
 * @author AndrewMs
 * @version 1.0
 */
public class ConsoleDrawView implements DrawView {
    private final PrintWriter out;

    public ConsoleDrawView(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void show() {
        out.println("-----");
        out.println("Draw.");
        out.println("-----");
    }
}
