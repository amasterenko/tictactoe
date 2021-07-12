package com.example.tictactoe.consolegame.input;

import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ConsoleStrValidateInputTest {
    private final String ln = System.lineSeparator();
    private final StringWriter stubWriter = new StringWriter();
    private final PrintWriter out = new PrintWriter(stubWriter);

    @Test
    public void whenAskAndPredicateTrueThenGetResult() {
        ConsoleStrValidateInput valStr = new ConsoleStrValidateInput(new Scanner("test"), out);
        assertEquals("test", valStr.ask("stub msg", s -> true).get());
    }

    @Test
    public void whenAskAndPredicateFalseThenCancelInput() {
        ConsoleStrValidateInput valStr = new ConsoleStrValidateInput(
                new Scanner("test" + ln + "n" + ln), out);
        assertEquals(Optional.empty(), valStr.ask("stub msg", s -> false));
    }

    @Test
    public void whenAskAndPredicateFalseThenRepeatInput() {
        ConsoleStrValidateInput valStr = new ConsoleStrValidateInput(
                new Scanner("" + ln + "y" + ln + "test" + ln), out);
        assertEquals("test", valStr.ask("stub msg", s -> !s.isEmpty()).get());
    }
}