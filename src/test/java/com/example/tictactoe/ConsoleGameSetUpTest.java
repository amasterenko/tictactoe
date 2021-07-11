package com.example.tictactoe;

import com.example.tictactoe.ConsoleGameSetUp;
import com.example.tictactoe.rules.GameRules;
import com.example.tictactoe.view.ConsoleBoardView;
import com.example.tictactoe.view.ConsoleDrawView;
import com.example.tictactoe.view.ConsoleWinView;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ConsoleGameSetUpTest {
    private String ln = System.lineSeparator();
    private final StringWriter strWriter = new StringWriter();
    private final PrintWriter out = new PrintWriter(strWriter);

    @Test
    public void getBoardWithoutInit() {
        ConsoleGameSetUp setUp = new ConsoleGameSetUp(new Scanner(""), out);
        assertEquals(3, setUp.getBoard().getState().length);
    }

    @Test
    public void getPlayer1WithoutInit() {
        ConsoleGameSetUp setUp = new ConsoleGameSetUp(new Scanner(""), out);
        assertEquals("player1", setUp.getPlayer1().getName());
    }

    @Test
    public void getPlayer2WithoutInit() {
        ConsoleGameSetUp setUp = new ConsoleGameSetUp(new Scanner(""), out);
        assertEquals("player2", setUp.getPlayer2().getName());
    }

    @Test
    public void getBoardView() {
        ConsoleGameSetUp setUp = new ConsoleGameSetUp(new Scanner(""), out);
        assertEquals(ConsoleBoardView.class, setUp.getBoardView().getClass());
    }

    @Test
    public void getWinViewWithoutInit() {
        ConsoleGameSetUp setUp = new ConsoleGameSetUp(new Scanner(""), out);
        assertEquals(ConsoleWinView.class, setUp.getWinView().getClass());
    }

    @Test
    public void getDrawView() {
        ConsoleGameSetUp setUp = new ConsoleGameSetUp(new Scanner(""), out);
        assertEquals(ConsoleDrawView.class, setUp.getDrawView().getClass());
    }

    @Test
    public void getRules() {
        ConsoleGameSetUp setUp = new ConsoleGameSetUp(new Scanner(""), out);
        assertEquals(GameRules.class, setUp.getRules().getClass());
    }

    @Test
    public void init5x5BoardAndTwoHumanPlayers() {
        String answers = "5" + ln + "1" + ln + "PlayerA" + ln + "1" + ln + "PlayerB" + ln;
        ConsoleGameSetUp setUp = new ConsoleGameSetUp(new Scanner(answers), out);
        setUp.init();
        assertEquals(5, setUp.getBoard().getState().length);
        assertEquals("PlayerA", setUp.getPlayer1().getName());
        assertEquals("PlayerB", setUp.getPlayer2().getName());
    }

    @Test
    public void init5x5BoardOneHumanAndOneCompPlayers() {
        String answers = "5" + ln + "1" + ln + "PlayerA" + ln + "2" + ln;
        ConsoleGameSetUp setUp = new ConsoleGameSetUp(new Scanner(answers), out);
        setUp.init();
        assertEquals(5, setUp.getBoard().getState().length);
        assertEquals("PlayerA", setUp.getPlayer1().getName());
        assertEquals("ComputerPlayer1", setUp.getPlayer2().getName());
    }

    @Test
    public void init5x5BoardAndTwoCompPlayers() {
        String answers = "5" + ln + "2" + ln + "2" + ln;
        ConsoleGameSetUp setUp = new ConsoleGameSetUp(new Scanner(answers), out);
        setUp.init();
        assertEquals(5, setUp.getBoard().getState().length);
        assertEquals("ComputerPlayer1", setUp.getPlayer1().getName());
        assertEquals("ComputerPlayer2", setUp.getPlayer2().getName());
    }

    @Test
    public void whenInitAndWrongBoardSizeAndExit() {
        String answers = "1" + ln + "n" + ln;
        ConsoleGameSetUp setUp = new ConsoleGameSetUp(new Scanner(answers), out);
        String expectedOut = "Input the game board size (2-20, 0 for exit):"
                + ln + "Wrong value. Try again? (y/n)" + ln;
        setUp.init();
        assertEquals(expectedOut, strWriter.toString());
    }

    @Test
    public void whenInitAndRepeatBoardSizeInput() {
        String answers = "1" + ln + "y" + ln + 25 + ln + "n" + ln;
        ConsoleGameSetUp setUp = new ConsoleGameSetUp(new Scanner(answers), out);
        String expectedOut = "Input the game board size (2-20, 0 for exit):" + ln
                + "Wrong value. Try again? (y/n)" + ln
                + "Input the game board size (2-20, 0 for exit):" + ln
                + "Wrong value. Try again? (y/n)" + ln;
        setUp.init();
        assertEquals(expectedOut, strWriter.toString());
    }
}