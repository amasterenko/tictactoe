package com.example.tictactoe.consolegame.view;

import com.example.tictactoe.consolegame.input.ConsoleIntValidateInput;
import com.example.tictactoe.player.ConsolePlayer;
import com.example.tictactoe.player.Player;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ConsoleWinViewTest {
    private final String ln = System.lineSeparator();
    private final StringWriter stubWriter = new StringWriter();
    private final PrintWriter out = new PrintWriter(stubWriter);

    @Test
    public void show() {
        ConsoleWinView view = new ConsoleWinView(out);
        String name = "Player1";
        Player winner = new ConsolePlayer(name,
                new ConsoleIntValidateInput(new Scanner(""), out), out);
        String expected = "------------" + ln + name + " won!" + ln + "------------" + ln;
        view.show(winner);
        assertEquals(expected, stubWriter.toString());
    }
}