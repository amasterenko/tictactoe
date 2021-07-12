package com.example.tictactoe.consolegame.input;

import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ConsoleIntValidateInputTest {
    private final String ln = System.lineSeparator();
    private final StringWriter stubWriter = new StringWriter();
    private final PrintWriter out = new PrintWriter(stubWriter);

    @Test
    public void whenAskAndPredicateTrueThenGetResult() {
        ValidateInput<Integer> valInt = new ConsoleIntValidateInput(new Scanner("1"), out);
        assertEquals(Optional.of(1), valInt.ask("stub msg", i -> true));
    }

    @Test
    public void whenAskAndPredicateFalseThenCancelInput() {
        ConsoleIntValidateInput valInt = new ConsoleIntValidateInput(
                new Scanner("1" + ln + "n" + ln), out);
        assertEquals(Optional.empty(), valInt.ask("stub msg", s -> false));
    }

    @Test
    public void whenAskAndPredicateFalseThenRepeatInput() {
        ConsoleIntValidateInput valInt = new ConsoleIntValidateInput(
                new Scanner("0" + ln + "y" + ln + "1" + ln), out);
        assertEquals(Optional.of(1), valInt.ask("stub msg", s -> !(s == 0)));
    }
}