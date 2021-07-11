package com.example.tictactoe;

import com.example.tictactoe.ConsoleGame;
import com.example.tictactoe.ConsoleGameSetUp;
import com.example.tictactoe.logic.GameFlow;
import com.example.tictactoe.player.ConsolePlayer;
import com.example.tictactoe.player.Player;
import com.example.tictactoe.view.ConsoleDrawView;
import com.example.tictactoe.view.ConsoleWinView;
import com.example.tictactoe.view.DrawView;
import com.example.tictactoe.view.WinView;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

public class ConsoleGameTest {
    private final String ln = System.lineSeparator();
    private final StringWriter stubWriter = new StringWriter();
    private final PrintWriter out = new PrintWriter(stubWriter);

    @Test
    public void whenStartThenDraw() {
        ConsoleGameSetUp setUp = Mockito.mock(ConsoleGameSetUp.class);
        GameFlow gameFlow = Mockito.mock(GameFlow.class);
        DrawView drawView = Mockito.mock(ConsoleDrawView.class);
        Player player1 = new ConsolePlayer("A", new Scanner("1"), out);
        Player player2 = new ConsolePlayer("B", new Scanner("1"), out);
        Mockito.when(setUp.init()).thenReturn(true);
        Mockito.when(setUp.getPlayer1()).thenReturn(player1);
        Mockito.when(setUp.getPlayer2()).thenReturn(player2);
        Mockito.when(gameFlow.start()).thenReturn(0);
        Mockito.when(setUp.getDrawView()).thenReturn(drawView);
        ConsoleGame game = new ConsoleGame(setUp, gameFlow,
                new Scanner("1" + ln + "n" + ln + "2" + ln), out);
        String expectOut = "---X/O GAME---" + ln + "1. New Game" + ln
                + "2. Exit" + ln + "Select option:" + ln + "Next round? (y/n)" + ln
                + "1. New Game" + ln + "2. Exit" + ln + "Select option:" + ln;
        game.start();

        Mockito.verify(setUp, times(1)).getDrawView();
        Mockito.verify(setUp, times(0)).getWinView();
        assertEquals(expectOut, stubWriter.toString());
    }

    @Test
    public void whenStartThenWin() {
        ConsoleGameSetUp setUp = Mockito.mock(ConsoleGameSetUp.class);
        GameFlow gameFlow = Mockito.mock(GameFlow.class);
        WinView winView = Mockito.mock(ConsoleWinView.class);
        Player player1 = new ConsolePlayer("A", new Scanner("1"), out);
        Player player2 = new ConsolePlayer("B", new Scanner("1"), out);
        Mockito.when(setUp.init()).thenReturn(true);
        Mockito.when(setUp.getPlayer1()).thenReturn(player1);
        Mockito.when(setUp.getPlayer2()).thenReturn(player2);
        Mockito.when(gameFlow.start()).thenReturn(1);
        Mockito.when(setUp.getWinView()).thenReturn(winView);
        ConsoleGame game = new ConsoleGame(setUp, gameFlow,
                new Scanner("1" + ln + "n" + ln + "2" + ln), out);
        String expectOut = "---X/O GAME---" + ln + "1. New Game" + ln
                + "2. Exit" + ln + "Select option:" + ln + "Next round? (y/n)" + ln
                + "1. New Game" + ln + "2. Exit" + ln + "Select option:" + ln;
        game.start();

        Mockito.verify(setUp, times(0)).getDrawView();
        Mockito.verify(setUp, times(1)).getWinView();
        assertEquals(expectOut, stubWriter.toString());
    }

    @Test
    public void whenSetupGameCanceledThenReturnToManiMenu() {
        ConsoleGameSetUp setUp = Mockito.mock(ConsoleGameSetUp.class);
        GameFlow gameFlow = Mockito.mock(GameFlow.class);
        Mockito.when(setUp.init()).thenReturn(false);
        ConsoleGame game = new ConsoleGame(setUp, gameFlow,
                new Scanner("1" + ln + "2" + ln), out);
        String expectOut = "---X/O GAME---" + ln + "1. New Game" + ln
                + "2. Exit" + ln + "Select option:" + ln
                + "1. New Game" + ln + "2. Exit" + ln + "Select option:" + ln;
        game.start();

        Mockito.verify(gameFlow, times(0)).start();
        Mockito.verify(setUp, times(0)).getDrawView();
        Mockito.verify(setUp, times(0)).getWinView();
        assertEquals(expectOut, stubWriter.toString());
    }

    @Test
    public void whenGameCancelledThenReturnToManiMenu() {
        ConsoleGameSetUp setUp = Mockito.mock(ConsoleGameSetUp.class);
        GameFlow gameFlow = Mockito.mock(GameFlow.class);
        Player player1 = new ConsolePlayer("A", new Scanner("1"), out);
        Player player2 = new ConsolePlayer("B", new Scanner("1"), out);
        Mockito.when(setUp.init()).thenReturn(true);
        Mockito.when(setUp.getPlayer1()).thenReturn(player1);
        Mockito.when(setUp.getPlayer2()).thenReturn(player2);
        Mockito.when(gameFlow.start()).thenThrow(new IllegalStateException("cancel!"));
        ConsoleGame game = new ConsoleGame(setUp, gameFlow,
                new Scanner("1" + ln  + "2" + ln), out);
        String expectOut = "---X/O GAME---" + ln + "1. New Game" + ln
                + "2. Exit" + ln + "Select option:" + ln
                + "1. New Game" + ln + "2. Exit" + ln + "Select option:" + ln;
        game.start();

        Mockito.verify(gameFlow, times(1)).start();
        Mockito.verify(setUp, times(0)).getDrawView();
        Mockito.verify(setUp, times(0)).getWinView();
        assertEquals(expectOut, stubWriter.toString());
    }

}