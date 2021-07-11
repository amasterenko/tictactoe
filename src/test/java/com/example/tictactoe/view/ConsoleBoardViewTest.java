package com.example.tictactoe.view;

import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class ConsoleBoardViewTest {
    private final String ln = System.lineSeparator();
    private final StringWriter stubWriter = new StringWriter();
    private final PrintWriter out = new PrintWriter(stubWriter);

    @Test
    public void showEmptyBoard3x3() {
        Mark<PrintWriter> mark = new ConsoleMark('X', 'O');
        ConsoleBoardView view = new ConsoleBoardView(out, mark);
        int[][] boardState = new int[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        String expected = "     1   2   3 " + ln
                + "   +---+---+---+" + ln
                + "1  |   |   |   |" + ln
                + "   +---+---+---+" + ln
                + "2  |   |   |   |" + ln
                + "   +---+---+---+" + ln
                + "3  |   |   |   |" + ln
                + "   +---+---+---+" + ln;
        view.show(boardState);
        assertEquals(expected, stubWriter.toString());
    }

    @Test
    public void showBoard3x3HavingOneXDiagonal() {
        Mark<PrintWriter> mark = new ConsoleMark('X', 'O');
        ConsoleBoardView view = new ConsoleBoardView(out, mark);
        int[][] boardState = new int[][] {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        String expected = "     1   2   3 " + ln
                + "   +---+---+---+" + ln
                + "1  | X |   |   |" + ln
                + "   +---+---+---+" + ln
                + "2  |   | X |   |" + ln
                + "   +---+---+---+" + ln
                + "3  |   |   | X |" + ln
                + "   +---+---+---+" + ln;
        view.show(boardState);
        assertEquals(expected, stubWriter.toString());
    }

    @Test
    public void showBoard3x3HavingTwoHorizontalLines() {
        Mark<PrintWriter> mark = new ConsoleMark('X', 'O');
        ConsoleBoardView view = new ConsoleBoardView(out, mark);
        int[][] boardState = new int[][] {{2, 2, 2}, {0, 0, 0}, {1, 1, 1}};
        String expected = "     1   2   3 " + ln
                + "   +---+---+---+" + ln
                + "1  | O | O | O |" + ln
                + "   +---+---+---+" + ln
                + "2  |   |   |   |" + ln
                + "   +---+---+---+" + ln
                + "3  | X | X | X |" + ln
                + "   +---+---+---+" + ln;
        view.show(boardState);
        assertEquals(expected, stubWriter.toString());
    }

    @Test
    public void showFullXBoard3x3() {
        Mark<PrintWriter> mark = new ConsoleMark('X', 'O');
        ConsoleBoardView view = new ConsoleBoardView(out, mark);
        int[][] boardState = new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        String expected = "     1   2   3 " + ln
                + "   +---+---+---+" + ln
                + "1  | X | X | X |" + ln
                + "   +---+---+---+" + ln
                + "2  | X | X | X |" + ln
                + "   +---+---+---+" + ln
                + "3  | X | X | X |" + ln
                + "   +---+---+---+" + ln;
        view.show(boardState);
        assertEquals(expected, stubWriter.toString());
    }

    @Test
    public void showFullOBoard3x3() {
        Mark<PrintWriter> mark = new ConsoleMark('X', 'O');
        ConsoleBoardView view = new ConsoleBoardView(out, mark);
        int[][] boardState = new int[][] {{2, 2, 2}, {2, 2, 2}, {2, 2, 2}};
        String expected = "     1   2   3 " + ln
                + "   +---+---+---+" + ln
                + "1  | O | O | O |" + ln
                + "   +---+---+---+" + ln
                + "2  | O | O | O |" + ln
                + "   +---+---+---+" + ln
                + "3  | O | O | O |" + ln
                + "   +---+---+---+" + ln;
        view.show(boardState);
        assertEquals(expected, stubWriter.toString());
    }
}