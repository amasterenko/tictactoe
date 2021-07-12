package com.example.tictactoe.consolegame;

import com.example.tictactoe.consolegame.init.*;
import com.example.tictactoe.logic.GameFlow;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;

public class ConsoleGameTest {
    private final String ln = System.lineSeparator();
    private final StringWriter stubWriter = new StringWriter();
    private final PrintWriter out = new PrintWriter(stubWriter);

    @Test
    public void whenStartThenMenuShow() {
        GameFlow gameFlow = Mockito.mock(GameFlow.class);
        GameRounds rounds = Mockito.mock(GameRounds.class);
        ConsoleGameInit initHH = Mockito.mock(ConsoleGameInitHH.class);
        ConsoleGameInit initHC = Mockito.mock(ConsoleGameInitHC.class);
        ConsoleGameInit initCC = Mockito.mock(ConsoleGameInitCC.class);
        Mockito.when(initHH.getGameType()).thenReturn(GameType.HUMAN_HUMAN);
        Mockito.when(initHC.getGameType()).thenReturn(GameType.HUMAN_COMPUTER);
        Mockito.when(initCC.getGameType()).thenReturn(GameType.COMPUTER_COMPUTER);
        Scanner in = new Scanner("4");
        ConsoleGame game = new ConsoleGame(List.of(initHH, initHC, initCC),
                gameFlow, rounds, in, out);
        game.start();
        String expected = "---X/O GAME---" + ln
                + "1. New human-human game" + ln
                + "2. New human-computer game" + ln
                + "3. New computer-computer game" + ln
                + "4. Exit" + ln + "Select option:" + ln;
        assertEquals(expected, stubWriter.toString());
        Mockito.verify(rounds, times(0)).play(any(), any());
    }

    @Test
    public void whenStartAndFirstOptionSelect() {
        GameFlow gameFlow = Mockito.mock(GameFlow.class);
        GameRounds rounds = Mockito.mock(GameRounds.class);
        ConsoleGameInit initHH = Mockito.mock(ConsoleGameInitHH.class);
        ConsoleGameInit initHC = Mockito.mock(ConsoleGameInitHC.class);
        ConsoleGameInit initCC = Mockito.mock(ConsoleGameInitCC.class);
        Mockito.when(initHH.getGameType()).thenReturn(GameType.HUMAN_HUMAN);
        Mockito.when(initHC.getGameType()).thenReturn(GameType.HUMAN_COMPUTER);
        Mockito.when(initCC.getGameType()).thenReturn(GameType.COMPUTER_COMPUTER);
        Mockito.when(initHH.init(anyInt(), anyInt())).thenReturn(true);
        Scanner in = new Scanner("1" + ln + "4");
        ConsoleGame game = new ConsoleGame(List.of(initHH, initHC, initCC),
                gameFlow, rounds, in, out);
        game.start();
        String expected = "---X/O GAME---" + ln
                + "1. New human-human game" + ln
                + "2. New human-computer game" + ln
                + "3. New computer-computer game" + ln
                + "4. Exit" + ln + "Select option:" + ln
                + "1. New human-human game" + ln
                + "2. New human-computer game" + ln
                + "3. New computer-computer game" + ln
                + "4. Exit" + ln + "Select option:" + ln;
        assertEquals(expected, stubWriter.toString());
        Mockito.verify(rounds, times(1)).play(initHH, gameFlow);
    }

    @Test
    public void whenStartAndSecondOptionSelect() {
        GameFlow gameFlow = Mockito.mock(GameFlow.class);
        GameRounds rounds = Mockito.mock(GameRounds.class);
        ConsoleGameInit initHH = Mockito.mock(ConsoleGameInitHH.class);
        ConsoleGameInit initHC = Mockito.mock(ConsoleGameInitHC.class);
        ConsoleGameInit initCC = Mockito.mock(ConsoleGameInitCC.class);
        Mockito.when(initHH.getGameType()).thenReturn(GameType.HUMAN_HUMAN);
        Mockito.when(initHC.getGameType()).thenReturn(GameType.HUMAN_COMPUTER);
        Mockito.when(initCC.getGameType()).thenReturn(GameType.COMPUTER_COMPUTER);
        Mockito.when(initHC.init(anyInt(), anyInt())).thenReturn(true);
        Scanner in = new Scanner("2" + ln + "4");
        ConsoleGame game = new ConsoleGame(List.of(initHH, initHC, initCC),
                gameFlow, rounds, in, out);
        game.start();
        String expected = "---X/O GAME---" + ln
                + "1. New human-human game" + ln
                + "2. New human-computer game" + ln
                + "3. New computer-computer game" + ln
                + "4. Exit" + ln + "Select option:" + ln
                + "1. New human-human game" + ln
                + "2. New human-computer game" + ln
                + "3. New computer-computer game" + ln
                + "4. Exit" + ln + "Select option:" + ln;
        assertEquals(expected, stubWriter.toString());
        Mockito.verify(rounds, times(1)).play(initHC, gameFlow);
    }

    @Test
    public void whenStartAndThirdOptionSelect() {
        GameFlow gameFlow = Mockito.mock(GameFlow.class);
        GameRounds rounds = Mockito.mock(GameRounds.class);
        ConsoleGameInit initHH = Mockito.mock(ConsoleGameInitHH.class);
        ConsoleGameInit initHC = Mockito.mock(ConsoleGameInitHC.class);
        ConsoleGameInit initCC = Mockito.mock(ConsoleGameInitCC.class);
        Mockito.when(initHH.getGameType()).thenReturn(GameType.HUMAN_HUMAN);
        Mockito.when(initHC.getGameType()).thenReturn(GameType.HUMAN_COMPUTER);
        Mockito.when(initCC.getGameType()).thenReturn(GameType.COMPUTER_COMPUTER);
        Mockito.when(initCC.init(anyInt(), anyInt())).thenReturn(true);
        Scanner in = new Scanner("3" + ln + "4");
        ConsoleGame game = new ConsoleGame(List.of(initHH, initHC, initCC),
                gameFlow, rounds, in, out);
        game.start();
        String expected = "---X/O GAME---" + ln
                + "1. New human-human game" + ln
                + "2. New human-computer game" + ln
                + "3. New computer-computer game" + ln
                + "4. Exit" + ln + "Select option:" + ln
                + "1. New human-human game" + ln
                + "2. New human-computer game" + ln
                + "3. New computer-computer game" + ln
                + "4. Exit" + ln + "Select option:" + ln;
        assertEquals(expected, stubWriter.toString());
        Mockito.verify(rounds, times(1)).play(initCC, gameFlow);
    }
}