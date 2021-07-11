package org.example.player;

import org.example.board.Board;
import org.example.board.GameBoard;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ComputerPlayerTest {
    private final String ln = System.lineSeparator();
    private final StringWriter stubWriter = new StringWriter();
    private final PrintWriter out = new PrintWriter(stubWriter);

    @Test
    public void makeTurn() {
        int size = 3;
        Board board = new GameBoard(size);
        Player player = new ComputerPlayer("PlayerName", out);
        int[][] boardState = board.getState();
        for (int i = 0; i < Math.pow(size, 2); i++) {
            board.takeTurn(player.makeTurn(boardState, 1), 1);
            boardState = board.getState();
        }
        assertTrue(board.isCompleted());
    }

    @Test
    public void getName() {
        Player player = new ComputerPlayer("PlayerName", out);
        assertEquals("PlayerName", player.getName());
    }
}