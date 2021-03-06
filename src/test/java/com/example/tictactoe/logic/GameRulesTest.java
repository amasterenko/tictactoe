package com.example.tictactoe.logic;

import com.example.tictactoe.board.Board;
import com.example.tictactoe.board.GameBoard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameRulesTest {
    private Board board;
    private Rules rules;

    @Before
    public void init() {
        board = new GameBoard(3);
        rules = new GameRules(board);
    }

    @Test
    public void whenNextPlayerAndFirstTurnThen1() {
        assertEquals(1, rules.nextPlayer());
    }

    @Test
    public void whenNoNextPlayer() {
        board.takeTurn(new int[]{1, 1}, 1);
        board.takeTurn(new int[]{1, 2}, 2);
        board.takeTurn(new int[]{1, 3}, 1);
        board.takeTurn(new int[]{2, 1}, 2);
        board.takeTurn(new int[]{2, 2}, 1);
        board.takeTurn(new int[]{2, 3}, 2);
        board.takeTurn(new int[]{3, 1}, 1);
        board.takeTurn(new int[]{3, 2}, 2);
        board.takeTurn(new int[]{3, 3}, 1);
        assertEquals(0, rules.nextPlayer());
    }

    @Test
    public void whenOneTurnThenIsNextTurnPossibleTrue() {
        board.takeTurn(new int[]{1, 1}, 1);
        assertTrue(rules.isNextTurnPossible());
    }

    @Test
    public void whenBoardIsCompletedThenIsNextTurnPossibleFalse() {
        board.takeTurn(new int[]{1, 1}, 1);
        board.takeTurn(new int[]{1, 2}, 2);
        board.takeTurn(new int[]{1, 3}, 1);
        board.takeTurn(new int[]{2, 1}, 2);
        board.takeTurn(new int[]{2, 2}, 1);
        board.takeTurn(new int[]{2, 3}, 2);
        board.takeTurn(new int[]{3, 1}, 1);
        board.takeTurn(new int[]{3, 2}, 2);
        board.takeTurn(new int[]{3, 3}, 1);
        assertFalse(rules.isNextTurnPossible());
    }

    @Test
    public void whenWinnerExistsThenIsNextTurnPossibleFalse() {
        board.takeTurn(new int[]{1, 1}, rules.nextPlayer());
        board.takeTurn(new int[]{2, 1}, rules.nextPlayer());
        board.takeTurn(new int[]{1, 2}, rules.nextPlayer());
        board.takeTurn(new int[]{2, 3}, rules.nextPlayer());
        board.takeTurn(new int[]{1, 3}, rules.nextPlayer());
        assertFalse(rules.isNextTurnPossible());
    }

    @Test
    public void whenGetWinnerAndWinnerExistsThenWinnerNum() {
        int first = rules.nextPlayer();
        board.takeTurn(new int[]{1, 1}, first);
        board.takeTurn(new int[]{2, 1}, rules.nextPlayer());
        board.takeTurn(new int[]{1, 2}, rules.nextPlayer());
        board.takeTurn(new int[]{2, 3}, rules.nextPlayer());
        board.takeTurn(new int[]{1, 3}, rules.nextPlayer());
        assertEquals(first, rules.getWinner());
    }

    @Test
    public void whenGetWinnerAndBoardNotCompletedAndNoWinnerThen0() {
        board.takeTurn(new int[]{1, 1}, rules.nextPlayer());
        board.takeTurn(new int[]{2, 1}, rules.nextPlayer());
        board.takeTurn(new int[]{1, 2}, rules.nextPlayer());
        board.takeTurn(new int[]{2, 3}, rules.nextPlayer());
        assertEquals(0, rules.getWinner());
    }

    @Test
    public void whenDrawAndGetWinnerThen0() {
        board.takeTurn(new int[]{1, 1}, rules.nextPlayer());
        board.takeTurn(new int[]{2, 1}, rules.nextPlayer());
        board.takeTurn(new int[]{1, 2}, rules.nextPlayer());
        board.takeTurn(new int[]{2, 2}, rules.nextPlayer());
        board.takeTurn(new int[]{3, 1}, rules.nextPlayer());
        board.takeTurn(new int[]{3, 2}, rules.nextPlayer());
        board.takeTurn(new int[]{2, 3}, rules.nextPlayer());
        board.takeTurn(new int[]{1, 3}, rules.nextPlayer());
        board.takeTurn(new int[]{3, 3}, rules.nextPlayer());
        assertEquals(0, rules.getWinner());
    }

    @Test
    public void whenReset() {
        int first = rules.nextPlayer();
        board.takeTurn(new int[]{1, 1}, first);
        board.takeTurn(new int[]{2, 1}, rules.nextPlayer());
        board.takeTurn(new int[]{1, 2}, rules.nextPlayer());
        board.takeTurn(new int[]{2, 3}, rules.nextPlayer());
        board.takeTurn(new int[]{1, 3}, rules.nextPlayer());
        assertEquals(first, rules.getWinner());
        assertFalse(rules.isNextTurnPossible());
        rules.reset();
        assertEquals(0, rules.getWinner());
        assertTrue(rules.isNextTurnPossible());
        assertEquals(1, rules.nextPlayer());
    }
}