package com.example.tictactoe.consolegame.init;

import com.example.tictactoe.consolegame.view.BoardView;
import com.example.tictactoe.consolegame.view.DrawView;
import com.example.tictactoe.consolegame.view.Mark;
import com.example.tictactoe.consolegame.view.WinView;
import com.example.tictactoe.board.Board;
import com.example.tictactoe.player.Player;
import com.example.tictactoe.logic.Rules;

import java.io.PrintWriter;

public interface ConsoleGameInit {

    boolean init(int boardMinSize, int boardMaxSize);

    Player getPlayer1();

    Player getPlayer2();

    Rules getRules();

    Board getBoard();

    Mark<PrintWriter> getMark();

    BoardView getBoardView();

    WinView getWinView();

    DrawView getDrawView();

    GameType getGameType();
}
