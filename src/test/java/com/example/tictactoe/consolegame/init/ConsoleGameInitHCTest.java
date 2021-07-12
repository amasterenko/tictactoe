package com.example.tictactoe.consolegame.init;

import com.example.tictactoe.consolegame.view.ConsoleBoardView;
import com.example.tictactoe.consolegame.view.ConsoleDrawView;
import com.example.tictactoe.consolegame.view.ConsoleWinView;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ConsoleGameInitHCTest {
    private final String ln = System.lineSeparator();
    private final StringWriter stubWriter = new StringWriter();
    private final PrintWriter out = new PrintWriter(stubWriter);

    @Test
    public void whenInitBoardPlayer1FirstWithXComputerPlayerSecondWithO() {
        int size = 5;
        int boardMinSize = 1;
        int boardMaxSize = 10;
        String player1Name = "player1";
        String player2Name = "ComputerPlayer";
        char x = 'X';
        char o = 'O';
        String answers = size + ln + player1Name  + ln + "1" + ln + "1" + ln + player2Name + ln;
        ConsoleGameInit init = new ConsoleGameInitHC(new Scanner(answers), out);
        init.init(boardMinSize, boardMaxSize);
        assertEquals(size, init.getBoard().getState().length);
        assertEquals(player1Name, init.getPlayer1().getName());
        assertEquals(player2Name, init.getPlayer2().getName());
        assertEquals(x, init.getMark().getPlayerSymbols().get(1).charValue());
        assertEquals(o, init.getMark().getPlayerSymbols().get(2).charValue());
    }

    @Test
    public void whenInitBoardPlayer1SecondWithOComputerPlayer2FirstWithO() {
        int size = 5;
        int boardMinSize = 1;
        int boardMaxSize = 10;
        String player1Name = "player1";
        String player2Name = "ComputerPlayer";
        char x = 'X';
        char o = 'O';
        String answers = size + ln + player1Name  + ln + "2" + ln + "2"  + ln;
        ConsoleGameInit init = new ConsoleGameInitHC(new Scanner(answers), out);
        init.init(boardMinSize, boardMaxSize);
        assertEquals(size, init.getBoard().getState().length);
        assertEquals(player2Name, init.getPlayer1().getName());
        assertEquals(player1Name, init.getPlayer2().getName());
        assertEquals(x, init.getMark().getPlayerSymbols().get(1).charValue());
        assertEquals(o, init.getMark().getPlayerSymbols().get(2).charValue());
    }

    @Test
    public void getPlayer1WithoutInit() {
        String expected = "player1";
        ConsoleGameInit init = new ConsoleGameInitHC(new Scanner(""), out);
        assertEquals(expected, init.getPlayer1().getName());
    }

    @Test
    public void getPlayer2() {
        String expected = "ComputerPlayer";
        ConsoleGameInit init = new ConsoleGameInitHC(new Scanner(""), out);
        assertEquals(expected, init.getPlayer2().getName());
    }

    @Test
    public void getRulesWithoutInit() {
        ConsoleGameInit init = new ConsoleGameInitHC(new Scanner(""), out);
        assertTrue(init.getRules().isNextTurnPossible());
        assertEquals(0, init.getRules().getWinner());
        assertEquals(1, init.getRules().nextPlayer());
    }

    @Test
    public void getBoardWithoutInit() {
        ConsoleGameInit init = new ConsoleGameInitHC(new Scanner(""), out);
        assertEquals(3, init.getBoard().getState().length);
    }

    @Test
    public void getMarkWithoutInit() {
        ConsoleGameInit init = new ConsoleGameInitHC(new Scanner(""), out);
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
        ConsoleGameInit init = new ConsoleGameInitHC(new Scanner(""), out);
        assertEquals(ConsoleBoardView.class, init.getBoardView().getClass());
    }

    @Test
    public void getWinView() {
        ConsoleGameInit init = new ConsoleGameInitHC(new Scanner(""), out);
        assertEquals(ConsoleWinView.class, init.getWinView().getClass());
    }

    @Test
    public void getDrawView() {
        ConsoleGameInit init = new ConsoleGameInitHC(new Scanner(""), out);
        assertEquals(ConsoleDrawView.class, init.getDrawView().getClass());
    }
}