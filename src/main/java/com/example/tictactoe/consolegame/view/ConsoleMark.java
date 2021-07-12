package com.example.tictactoe.consolegame.view;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
/**
 * Class represent the game mark and its belonging to player.
 *
 * @author AndrewMs
 * @version 1.0
 */
public class ConsoleMark implements Mark<PrintWriter> {
    private final char x;
    private final char o;
    private final Map<Integer, Character> playerSymbols;

    public ConsoleMark(char x, char o) {
        this.x = x;
        this.o = o;
        this.playerSymbols = new HashMap<>(2);
        this.playerSymbols.put(1, x);
        this.playerSymbols.put(2, o);
    }

    @Override
    public void show(PrintWriter out, int playerNum) {
        out.print((" " + playerSymbols.getOrDefault(playerNum, ' ') + " "));
        out.flush();
    }

    @Override
    public void setX(int playerNum) {
        playerSymbols.put(playerNum, this.x);
    }

    @Override
    public void setO(int playerNum) {
        playerSymbols.put(playerNum, this.o);
    }

    @Override
    public Map<Integer, Character> getPlayerSymbols() {
        return playerSymbols;
    }
}
