package com.example.tictactoe.consolegame.view;

import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class ConsoleMarkTest {
    private final StringWriter stubWriter = new StringWriter();
    private final PrintWriter out = new PrintWriter(stubWriter);

    @Test
    public void showX() {
        Mark<PrintWriter> mark = new ConsoleMark('X', 'O');
        mark.show(out, 1);
        assertEquals(" X ", stubWriter.toString());
    }

    @Test
    public void showO() {
        Mark<PrintWriter> mark = new ConsoleMark('X', 'O');
        mark.show(out, 2);
        assertEquals(" O ", stubWriter.toString());
    }
}