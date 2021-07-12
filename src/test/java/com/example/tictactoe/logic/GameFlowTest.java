package com.example.tictactoe.logic;

import com.example.tictactoe.board.Board;
import com.example.tictactoe.board.GameBoard;
import com.example.tictactoe.consolegame.input.ConsoleIntValidateInput;
import com.example.tictactoe.player.ConsolePlayer;
import com.example.tictactoe.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import static org.junit.Assert.*;

public class GameFlowTest {
    private final String ln = System.lineSeparator();
    private final StringWriter stubWriter = new StringWriter();
    private final PrintWriter out = new PrintWriter(stubWriter);
    private Board board;
    private Rules rules;

    @Before
    public void init() {
        board = new GameBoard(3);
        rules = new GameRules(board);
    }

    @Test
    public void whenEmptyBoardAndTurn() {
        String player1Turns = "1" + ln + "1" + ln;
        Player player1 = new ConsolePlayer("A",
                new ConsoleIntValidateInput(new Scanner(player1Turns), out), out);
        Player player2 = new ConsolePlayer("B",
                new ConsoleIntValidateInput(new Scanner(""), out), out);
        Flow gameFlow = new GameFlow(rules, board, player1, player2);

        assertEquals(-1, gameFlow.turn());
    }

    @Test(expected = IllegalStateException.class)
    public void whenTurnCancelled() {
        String player1Turns = "1" + ln + (board.getState().length + 1) + ln + "n" + ln;
        Player player1 = new ConsolePlayer("A",
                new ConsoleIntValidateInput(new Scanner(player1Turns), out), out);
        Player player2 = new ConsolePlayer("B",
                new ConsoleIntValidateInput(new Scanner(""), out), out);
        Flow gameFlow = new GameFlow(rules, board, player1, player2);
        gameFlow.turn();
    }

    @Test
    public void whenBoard3x3AndTurnThenWin() {
        board.takeTurn(new int[]{1, 1}, rules.nextPlayer());
        board.takeTurn(new int[]{2, 2}, rules.nextPlayer());
        board.takeTurn(new int[]{1, 2}, rules.nextPlayer());
        board.takeTurn(new int[]{2, 3}, rules.nextPlayer());
        String player1Turns = "1" + ln + "3" + ln;
        String player2Turns = "";
        Player player1 = new ConsolePlayer("A",
                new ConsoleIntValidateInput(new Scanner(player1Turns), out), out);
        Player player2 = new ConsolePlayer("B",
                new ConsoleIntValidateInput(new Scanner(player2Turns), out), out);
        Flow gameFlow = new GameFlow(rules, board, player1, player2);
        gameFlow.turn();
        int res = gameFlow.turn();

        assertEquals(1, res);
    }

    @Test
    public void whenBoard3x3AndTurnThenDraw() {
        board.takeTurn(new int[]{1, 1}, rules.nextPlayer());
        board.takeTurn(new int[]{2, 1}, rules.nextPlayer());
        board.takeTurn(new int[]{1, 2}, rules.nextPlayer());
        board.takeTurn(new int[]{2, 2}, rules.nextPlayer());
        board.takeTurn(new int[]{3, 1}, rules.nextPlayer());
        board.takeTurn(new int[]{3, 2}, rules.nextPlayer());
        board.takeTurn(new int[]{2, 3}, rules.nextPlayer());
        board.takeTurn(new int[]{1, 3}, rules.nextPlayer());
        board.takeTurn(new int[]{3, 3}, rules.nextPlayer());

        String player2Turns = "3" + ln + "3" + ln;
        Player player1 = new ConsolePlayer("A",
                new ConsoleIntValidateInput(new Scanner(""), out), out);
        Player player2 = new ConsolePlayer("B",
                new ConsoleIntValidateInput(new Scanner(player2Turns), out), out);
        Flow gameFlow = new GameFlow(rules, board, player1, player2);
        gameFlow.turn();
        int res = gameFlow.turn();

        assertEquals(0, res);
    }

    @Test()
    public void whenReset() {
        board.takeTurn(new int[]{1, 1}, rules.nextPlayer());
        Player player1 = new ConsolePlayer("A",
                new ConsoleIntValidateInput(new Scanner(""), out), out);
        Player player2 = new ConsolePlayer("B",
                new ConsoleIntValidateInput(new Scanner(""), out), out);
        Flow gameFlow = new GameFlow(rules, board, player1, player2);

        assertEquals(1, board.getState()[0][0]);
        gameFlow.reset();
        assertEquals(0, board.getState()[0][0]);
        assertEquals(1, rules.nextPlayer());
    }
}