package com.example.tictactoe.view;

import java.io.PrintWriter;

public class ConsoleMark implements Mark<PrintWriter> {
    private final char x;
    private final char o;

    public ConsoleMark(char x, char o) {
        this.x = x;
        this.o = o;
    }

    @Override
    public void show(PrintWriter out, int playerNum) {
        if (playerNum == 1) {
            out.print((" " + x + " "));
            out.flush();
        } else {
            out.print((" " + o + " "));
            out.flush();
        }
    }
}
