package com.example.tictactoe.logic;

import com.example.tictactoe.board.Board;
import com.example.tictactoe.board.GameBoard;
import com.example.tictactoe.player.ConsolePlayer;
import com.example.tictactoe.player.Player;
import com.example.tictactoe.rules.GameRules;
import com.example.tictactoe.rules.Rules;
import com.example.tictactoe.view.BoardView;
import com.example.tictactoe.view.ConsoleBoardView;
import com.example.tictactoe.view.ConsoleMark;
import com.example.tictactoe.view.Mark;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import static org.junit.Assert.*;

public class GameFlowTest {
    private final String ln = System.lineSeparator();
    private final StringWriter stubWriter = new StringWriter();
    private final PrintWriter out = new PrintWriter(stubWriter);

    @Test
    public void whenStartAndWinner() {
        Board board = new GameBoard(3);
        Rules rules = new GameRules(board);
        String player1Turns = "1" + ln + "1" + ln + "1" + ln + "2" + ln + "1" + ln + "3" + ln;
        String player2Turns = "3" + ln + "1" + ln + "2" + ln + "3" + ln + "3" + ln + "3" + ln;
        Player player1 = new ConsolePlayer("A", new Scanner(player1Turns), out);
        Player player2 = new ConsolePlayer("B", new Scanner(player2Turns), out);
        Mark<PrintWriter> mark = new ConsoleMark('X', 'O');
        BoardView view = new ConsoleBoardView(out, mark);
        Flow gameFlow = new GameFlow(rules, board, view, player1, player2);
        int res = gameFlow.start();
        assertTrue(res == 0 || res == 1);
    }

    @Test
    public void whenStartAndDraw() {
        Board board = new GameBoard(3);
        Rules rules = new GameRules(board);
        String player1Turns = "1" + ln + "1" + ln + "1" + ln + "2" + ln + "3" + ln + "1" + ln
                + "2" + ln + "3" + ln + "3" + ln + "3" + ln;
        String player2Turns = "2" + ln + "1" + ln + "2" + ln + "2" + ln + "3" + ln + "2" + ln
                + "1" + ln + "3" + ln + "3" + ln + "3" + ln;
        Player player1 = new ConsolePlayer("A", new Scanner(player1Turns), out);
        Player player2 = new ConsolePlayer("B", new Scanner(player2Turns), out);
        Mark<PrintWriter> mark = new ConsoleMark('X', 'O');
        BoardView view = new ConsoleBoardView(out, mark);
        Flow gameFlow = new GameFlow(rules, board, view, player1, player2);
        assertEquals(0, gameFlow.start());
    }

    @Test(expected = IllegalStateException.class)
    public void whenStartAndCancelInputFromPlayer() {
        Board board = new GameBoard(3);
        Rules rules = new GameRules(board);
        String player1Turns = "10" + ln + "1" + ln + "n" + ln;
        String player2Turns = "2" + ln + "1" + ln + "2" + ln + "2";
        Player player1 = new ConsolePlayer("A", new Scanner(player1Turns), out);
        Player player2 = new ConsolePlayer("B", new Scanner(player2Turns), out);
        Mark<PrintWriter> mark = new ConsoleMark('X', 'O');
        BoardView view = new ConsoleBoardView(out, mark);
        Flow gameFlow = new GameFlow(rules, board, view, player1, player2);
        gameFlow.start();
    }
}