package com.example.tictactoe.player;

import com.example.tictactoe.consolegame.input.ConsoleIntValidateInput;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ConsolePlayerTest {
    private final String ln = System.lineSeparator();
    private final StringWriter stubWriter = new StringWriter();
    private final PrintWriter out = new PrintWriter(stubWriter);

    @Test(expected = IllegalStateException.class)
    public void makeWrongTurnAndCancel() {
        int[][] boardState = new int[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        String name = "PlayerA";
        Player player = new ConsolePlayer(name, new ConsoleIntValidateInput(
                new Scanner("5" + ln + "1" + ln + "n" + ln), out), out);
        String expected = name + " turns:" + ln + "Wrong value. Try again? (y/n)" + ln;
        player.makeTurn(boardState, 1);
        assertEquals(expected, stubWriter.toString());
    }

    @Test
    public void makeWrongTurnAndRepeat() {
        int[][] boardState = new int[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        String name = "PlayerA";
        Player player = new ConsolePlayer(name, new ConsoleIntValidateInput(
                new Scanner("5" + ln + "y" + ln + "1" + ln + "2" + ln), out), out);
        String expected = name + " turns:" + ln + "Input x:"  + ln
                + "Wrong value. Try again? (y/n)" + ln + "Input x:" + ln + "Input y:" + ln;
        int[] res = player.makeTurn(boardState, 1);
        assertEquals(expected, stubWriter.toString());
        assertEquals(1, res[0]);
        assertEquals(2, res[1]);
    }

    @Test
    public void makeTurn() {
        int[][] boardState = new int[][] {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        String name = "PlayerA";
        Player player = new ConsolePlayer(name, new ConsoleIntValidateInput(
                new Scanner("1" + ln + "2" + ln), out), out);
        String expected = name + " turns:" + ln + "Input x:" + ln + "Input y:" + ln;
        int[] res = player.makeTurn(boardState, 1);
        assertEquals(expected, stubWriter.toString());
        assertEquals(1, res[0]);
        assertEquals(2, res[1]);
    }

    @Test
    public void getName() {
        Player player = new ConsolePlayer("PlayerName", new ConsoleIntValidateInput(
                new Scanner(""), out), out);
        assertEquals("PlayerName", player.getName());
    }
}