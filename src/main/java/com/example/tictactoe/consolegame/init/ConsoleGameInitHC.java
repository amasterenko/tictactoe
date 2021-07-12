package com.example.tictactoe.consolegame.init;

import com.example.tictactoe.consolegame.view.*;
import com.example.tictactoe.board.Board;
import com.example.tictactoe.board.GameBoard;
import com.example.tictactoe.consolegame.input.ConsoleIntValidateInput;
import com.example.tictactoe.consolegame.input.ConsoleStrValidateInput;
import com.example.tictactoe.consolegame.input.ValidateInput;
import com.example.tictactoe.player.ComputerPlayer;
import com.example.tictactoe.player.ConsolePlayer;
import com.example.tictactoe.player.Player;
import com.example.tictactoe.logic.GameRules;
import com.example.tictactoe.logic.Rules;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class represents the game initialization for human-computer game.
 *
 * @author AndrewMs
 * @version 1.0
 */
public class ConsoleGameInitHC implements ConsoleGameInit {
    private final GameType gameType;
    private final Scanner in;
    private final PrintWriter out;
    private final ValidateInput<Integer> inputInt;
    private final ValidateInput<String> inputStr;
    private Player player1;
    private Player player2;
    private Board board;
    private Rules rules;
    private final Mark<PrintWriter> mark;
    private final BoardView boardView;
    private final WinView winView;
    private final DrawView drawView;

    public ConsoleGameInitHC(Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        this.gameType = GameType.HUMAN_COMPUTER;
        this.inputInt = new ConsoleIntValidateInput(in, out);
        this.inputStr = new ConsoleStrValidateInput(in, out);
        this.board = new GameBoard(3);
        this.mark = new ConsoleMark('X', 'O');
        this.boardView = new ConsoleBoardView(out, mark);
        this.rules = new GameRules(board);
        this.winView = new ConsoleWinView(out);
        this.drawView = new ConsoleDrawView(out);
        this.player1 = new ConsolePlayer("player1", inputInt, out);
        this.player2 = new ComputerPlayer("ComputerPlayer", out);
    }

    @Override
    public boolean init(int boardMinSize, int boardMaxSize) {
        try {
            int size = inputInt.ask("Input the game board size (" + boardMinSize + ".."
                    + boardMaxSize + "):", s -> s >= boardMinSize && s <= boardMaxSize).get();
            String name1 = inputStr.ask("Input the player's name:", str -> !str.isEmpty()).get();
            int firstTurnPrior = inputInt.ask("Input your player number (1/2):",
                    p -> p == 1 || p == 2).get();
            int markType = inputInt.ask("Select your mark type (1 for X, 2 for O):",
                    p -> p == 1 || p == 2).get();
            if (firstTurnPrior == 1) {
                player1 = new ConsolePlayer(name1, inputInt, out);
                player2 = new ComputerPlayer("ComputerPlayer", out);
                mark.setX(markType == 1 ? 1 : 2);
                mark.setO(markType == 2 ? 1 : 2);
            }
            if (firstTurnPrior == 2) {
                player1 = new ComputerPlayer("ComputerPlayer", out);
                player2 = new ConsolePlayer(name1, inputInt, out);
                mark.setX(markType == 1 ? 2 : 1);
                mark.setO(markType == 2 ? 2 : 1);
            }
            board = new GameBoard(size);
            rules = new GameRules(board);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    @Override
    public Player getPlayer1() {
        return this.player1;
    }

    @Override
    public Player getPlayer2() {
        return this.player2;
    }

    @Override
    public Rules getRules() {
        return this.rules;
    }

    @Override
    public Board getBoard() {
        return this.board;
    }

    @Override
    public Mark<PrintWriter> getMark() {
        return this.mark;
    }

    @Override
    public BoardView getBoardView() {
        return this.boardView;
    }

    @Override
    public WinView getWinView() {
        return this.winView;
    }

    @Override
    public DrawView getDrawView() {
        return this.drawView;
    }

    @Override
    public GameType getGameType() {
        return this.gameType;
    }
}
