package com.example.tictactoe.consolegame;

import com.example.tictactoe.consolegame.init.ConsoleGameInit;
import com.example.tictactoe.consolegame.init.ConsoleGameInitHH;
import com.example.tictactoe.logic.GameFlow;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ConsoleGameRoundsTest {
    private final String ln = System.lineSeparator();
    private final StringWriter stubWriter = new StringWriter();
    private final PrintWriter out = new PrintWriter(stubWriter);

    @Test
    public void whenBoard3x3AndPlayThenPlayer1Wins() {
        GameFlow gameFlow = Mockito.mock(GameFlow.class);
        ConsoleGameInit init = new ConsoleGameInitHH(new Scanner(""), out);
        Mockito.when(gameFlow.turn()).thenReturn(1);
        GameRounds rounds = new ConsoleGameRounds(new Scanner("n"), out);
        rounds.play(init, gameFlow);
        assertTrue(stubWriter.toString().contains("player1 won!"));
    }

    @Test
    public void whenBoard3x3AndPlayThenDraw() {
        GameFlow gameFlow = Mockito.mock(GameFlow.class);
        ConsoleGameInit init = new ConsoleGameInitHH(new Scanner(""), out);
        Mockito.when(gameFlow.turn()).thenReturn(0);
        GameRounds rounds = new ConsoleGameRounds(new Scanner("n"), out);
        rounds.play(init, gameFlow);
        assertTrue(stubWriter.toString().contains("Draw."));
    }

    @Test
    public void whenBoard3x3WinAndRepeatGame() {
        GameFlow gameFlow = Mockito.mock(GameFlow.class);
        ConsoleGameInit init = new ConsoleGameInitHH(new Scanner(""), out);
        Mockito.when(gameFlow.turn()).thenReturn(0);
        GameRounds rounds = new ConsoleGameRounds(new Scanner("y" + ln + "n" + ln), out);
        rounds.play(init, gameFlow);
        assertTrue(stubWriter.toString().contains("Draw."));
    }

    @Test
    public void whenBoard3x3AndCancelTurn() {
        GameFlow gameFlow = Mockito.mock(GameFlow.class);
        ConsoleGameInit init = new ConsoleGameInitHH(new Scanner(""), out);
        Mockito.when(gameFlow.turn()).thenThrow(
                new IllegalStateException("the turn was cancelled"));
        GameRounds rounds = new ConsoleGameRounds(new Scanner("n" + ln), out);
        rounds.play(init, gameFlow);
        String expected =
                "     1   2   3 " + ln
                + "   +---+---+---+" + ln
                + "1  |   |   |   |" + ln
                + "   +---+---+---+" + ln
                + "2  |   |   |   |" + ln
                + "   +---+---+---+" + ln
                + "3  |   |   |   |" + ln
                + "   +---+---+---+" + ln
                + "Next round? (y/n)" + ln;
        assertEquals(expected, stubWriter.toString());
    }
}