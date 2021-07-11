package org.example.board;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameBoardTest {
    private Board board;

    @Before
    public void init() {
        board = new GameBoard(3);
    }

    @Test
    public void whenInitWith3SizeThenValidState() {
        int[][] state = board.getState();
        assertEquals(0, state[0][0]);
        assertEquals(0, state[0][1]);
        assertEquals(0, state[0][2]);
        assertEquals(0, state[1][0]);
        assertEquals(0, state[1][1]);
        assertEquals(0, state[1][2]);
        assertEquals(0, state[2][0]);
        assertEquals(0, state[2][1]);
        assertEquals(0, state[2][2]);
    }

    @Test
    public void when3x3BoardAndTakeTurnPlayer1ThenValidState() {
        board.takeTurn(new int[]{1, 1}, 1);
        int[][] state = board.getState();
        assertEquals(1, state[0][0]);
        assertEquals(0, state[0][1]);
        assertEquals(0, state[0][2]);
        assertEquals(0, state[1][0]);
        assertEquals(0, state[1][1]);
        assertEquals(0, state[1][2]);
        assertEquals(0, state[2][0]);
        assertEquals(0, state[2][1]);
        assertEquals(0, state[2][2]);
    }

    @Test
    public void when3x3BoardAndTakeTurnPlayer2ThenValidState() {
        board.takeTurn(new int[]{2, 2}, 2);
        int[][] state = board.getState();
        assertEquals(0, state[0][0]);
        assertEquals(0, state[0][1]);
        assertEquals(0, state[0][2]);
        assertEquals(0, state[1][0]);
        assertEquals(2, state[1][1]);
        assertEquals(0, state[1][2]);
        assertEquals(0, state[2][0]);
        assertEquals(0, state[2][1]);
        assertEquals(0, state[2][2]);
    }

    @Test
    public void when3x3BoardAndIsCompletedTrue() {
        assertFalse(board.isCompleted());
        board.takeTurn(new int[]{1, 1}, 1);
        board.takeTurn(new int[]{1, 2}, 1);
        board.takeTurn(new int[]{1, 3}, 1);
        board.takeTurn(new int[]{2, 1}, 1);
        board.takeTurn(new int[]{2, 2}, 1);
        board.takeTurn(new int[]{2, 3}, 1);
        board.takeTurn(new int[]{3, 1}, 1);
        board.takeTurn(new int[]{3, 2}, 1);
        board.takeTurn(new int[]{3, 3}, 1);
        assertTrue(board.isCompleted());
    }

    @Test
    public void when3x3BoardAndIsCompletedFalse() {
        assertFalse(board.isCompleted());
        board.takeTurn(new int[]{1, 1}, 1);
        board.takeTurn(new int[]{1, 2}, 1);
        board.takeTurn(new int[]{1, 3}, 1);
        board.takeTurn(new int[]{2, 1}, 1);
        board.takeTurn(new int[]{2, 2}, 1);
        board.takeTurn(new int[]{2, 3}, 1);
        board.takeTurn(new int[]{3, 1}, 1);
        board.takeTurn(new int[]{3, 2}, 1);

        assertFalse(board.isCompleted());
    }

    @Test
    public void when3x3BoardAndHasCompletedVerticalLine() {
        assertFalse(board.hasCompletedLine());
        board.takeTurn(new int[]{1, 1}, 1);
        board.takeTurn(new int[]{1, 2}, 1);
        board.takeTurn(new int[]{1, 3}, 1);
        assertTrue(board.hasCompletedLine());
    }

    @Test
    public void when3x3BoardAndCompletedHorizontalLine() {
        assertFalse(board.hasCompletedLine());
        board.takeTurn(new int[]{1, 1}, 1);
        board.takeTurn(new int[]{2, 1}, 1);
        board.takeTurn(new int[]{3, 1}, 1);
        assertTrue(board.hasCompletedLine());
    }

    @Test
    public void when3x3BoardAndCompletedDiagonal() {
        assertFalse(board.hasCompletedLine());
        board.takeTurn(new int[]{1, 1}, 1);
        board.takeTurn(new int[]{2, 2}, 1);
        board.takeTurn(new int[]{3, 3}, 1);
        assertTrue(board.hasCompletedLine());
    }

    @Test
    public void when3x3BoardAndReset() {
        board.takeTurn(new int[]{1, 1}, 1);
        board.takeTurn(new int[]{1, 2}, 1);
        board.takeTurn(new int[]{1, 3}, 1);
        board.reset();
        int[][] state = board.getState();
        assertEquals(0, state[0][0]);
        assertEquals(0, state[0][1]);
        assertEquals(0, state[0][2]);
        assertEquals(0, state[1][0]);
        assertEquals(0, state[1][1]);
        assertEquals(0, state[1][2]);
        assertEquals(0, state[2][0]);
        assertEquals(0, state[2][1]);
        assertEquals(0, state[2][2]);
    }
}