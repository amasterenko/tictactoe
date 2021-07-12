package com.example.tictactoe.consolegame.init;

import com.example.tictactoe.consolegame.view.*;
import com.example.tictactoe.board.Board;
import com.example.tictactoe.board.GameBoard;
import com.example.tictactoe.consolegame.input.ConsoleIntValidateInput;
import com.example.tictactoe.consolegame.input.ConsoleStrValidateInput;
import com.example.tictactoe.consolegame.input.ValidateInput;
import com.example.tictactoe.player.ComputerPlayer;
import com.example.tictactoe.player.Player;
import com.example.tictactoe.logic.GameRules;
import com.example.tictactoe.logic.Rules;

import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * Class represents the game initialization for computer-computer game.
 *
 * @author AndrewMs
 * @version 1.0
 */
public class ConsoleGameInitCC implements ConsoleGameInit {
    private final GameType gameType;
    private final Scanner in;
    private final PrintWriter out;
    private final ValidateInput<Integer> inputInt;
    private final ValidateInput<String> inputStr;
    private final Player player1;
    private final Player player2;
    private Board board;
    private Rules rules;
    private final Mark<PrintWriter> mark;
    private final BoardView boardView;
    private final WinView winView;
    private final DrawView drawView;

    public ConsoleGameInitCC(Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        this.gameType = GameType.COMPUTER_COMPUTER;
        this.inputInt = new ConsoleIntValidateInput(in, out);
        this.inputStr = new ConsoleStrValidateInput(in, out);
        this.board = new GameBoard(3);
        this.mark = new ConsoleMark('X', 'O');
        this.boardView = new ConsoleBoardView(out, mark);
        this.rules = new GameRules(board);
        this.winView = new ConsoleWinView(out);
        this.drawView = new ConsoleDrawView(out);
        this.player1 = new ComputerPlayer("ComputerPlayer1", out);
        this.player2 = new ComputerPlayer("ComputerPlayer2", out);
    }

    @Override
    public boolean init(int boardMinSize, int boardMaxSize) {
        try {
            int size = inputInt.ask("Input the game board size (" + boardMinSize + ".."
                    + boardMaxSize + "):", s -> s >= boardMinSize && s <= boardMaxSize).get();
            this.board = new GameBoard(size);
            this.rules = new GameRules(this.board);
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
