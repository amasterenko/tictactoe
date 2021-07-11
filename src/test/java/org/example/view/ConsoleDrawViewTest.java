package org.example.view;

import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class ConsoleDrawViewTest {
    private final String ln = System.lineSeparator();
    private final StringWriter stubWriter = new StringWriter();
    private final PrintWriter out = new PrintWriter(stubWriter);

    @Test
    public void show() {
        DrawView view = new ConsoleDrawView(out);
        String expected = "-----" + ln + "Draw." + ln + "-----" + ln;
        view.show();
        assertEquals(expected, stubWriter.toString());
    }
}