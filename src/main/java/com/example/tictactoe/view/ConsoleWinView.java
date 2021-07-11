package com.example.tictactoe.view;

import com.example.tictactoe.player.Player;

import java.io.PrintWriter;

public class ConsoleWinView implements WinView {
    private final PrintWriter out;

    public ConsoleWinView(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void show(Player player) {
        String str = player.getName() + " won!";
        out.println("-".repeat(str.length()));
        out.println(str);
        out.println("-".repeat(str.length()));
    }
}
