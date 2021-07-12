package com.example.tictactoe.consolegame.init;

import com.example.tictactoe.consolegame.view.ConsoleBoardView;
import com.example.tictactoe.consolegame.view.ConsoleDrawView;
import com.example.tictactoe.consolegame.view.ConsoleWinView;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ConsoleGameInitCCTest {
    private final String ln = System.lineSeparator();
    private final StringWriter stubWriter = new StringWriter();
    private final PrintWriter out = new PrintWriter(stubWriter);

    @Test
    public void initAndBoardSizeValid() {
        int size = 5;
        int boardMinSize = 1;
        int boardMaxSize = 10;
        ConsoleGameInit init = new ConsoleGameInitCC(new Scanner(size + ln), out);
        init.init(boardMinSize, boardMaxSize);
        assertEquals(size, init.getBoard().getState().length);
    }

    @Test
    public void initAndBoardSizeNotValid() {
        int size = 1;
        int boardMinSize = 5;
        int boardMaxSize = 10;
        ConsoleGameInit init = new ConsoleGameInitCC(new Scanner(size + ln + "n" + ln), out);
        init.init(5, 10);
        String expectedMsg = "Input the game board size (" + boardMinSize + ".."
                + boardMaxSize + "):" + ln + "Wrong value. Try again? (y/n)" + ln;
        assertEquals(3, init.getBoard().getState().length);
        assertEquals(expectedMsg, stubWriter.toString());
    }

    @Test
    public void getPlayer1() {
        String expected = "ComputerPlayer1";
        ConsoleGameInit init = new ConsoleGameInitCC(new Scanner(""), out);
        assertEquals(expected, init.getPlayer1().getName());
    }

    @Test
    public void getPlayer2() {
        String expected = "ComputerPlayer2";
        ConsoleGameInit init = new ConsoleGameInitCC(new Scanner(""), out);
        assertEquals(expected, init.getPlayer2().getName());
    }

    @Test
    public void getRules() {
        ConsoleGameInit init = new ConsoleGameInitCC(new Scanner(""), out);
        assertTrue(init.getRules().isNextTurnPossible());
        assertEquals(0, init.getRules().getWinner());
        assertEquals(1, init.getRules().nextPlayer());
    }

    @Test
    public void getBoardWithoutInit() {
        ConsoleGameInit init = new ConsoleGameInitCC(new Scanner(""), out);
        assertEquals(3, init.getBoard().getState().length);
    }

    @Test
    public void getMarkWithoutInit() {
        ConsoleGameInit init = new ConsoleGameInitCC(new Scanner(""), out);
        var symbols = init.getMark().getPlayerSymbols();
        char x = symbols.get(1);
        char o = symbols.get(2);
        assertTrue(x != o);
        assertEquals(2, symbols.keySet().size());
        assertTrue(symbols.containsKey(1));
        assertTrue(symbols.containsKey(2));
    }

    @Test
    public void getBoardView() {
        ConsoleGameInit init = new ConsoleGameInitCC(new Scanner(""), out);
        assertEquals(ConsoleBoardView.class, init.getBoardView().getClass());
    }

    @Test
    public void getWinView() {
        ConsoleGameInit init = new ConsoleGameInitCC(new Scanner(""), out);
        assertEquals(ConsoleWinView.class, init.getWinView().getClass());
    }

    @Test
    public void getDrawView() {
        ConsoleGameInit init = new ConsoleGameInitCC(new Scanner(""), out);
        assertEquals(ConsoleDrawView.class, init.getDrawView().getClass());
    }
}