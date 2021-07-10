package org.example;

import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleGameSetUp {
    private Scanner in;
    private PrintWriter out;
    private final static int MIN_BOARD_SIZE = 2;
    private final static int MAX_BOARD_SIZE = 20;
    private final Mark<PrintWriter> mark;
    private Board board;
    private final BoardView boardView;
    private Rules rules;
    private Player player1;
    private Player player2;

    public ConsoleGameSetUp(Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        this.board = new GameBoard(3);
        this.mark = new ConsoleMark('X', 'O');
        this.boardView = new ConsoleBoardView(out, mark);
        this.rules = new GameRules(board);
        this.player1 = new ConsolePlayer("player1", in, out, boardView);
        this.player2 = new ConsolePlayer("player2", in, out, boardView);
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public Rules getRules() {
        return rules;
    }

    public boolean init() {
        boolean exit = false;
        int size = 2;
        while (!exit) {
            out.println("Input the game board size (2-20, 0 for exit):");
            try {
                size = Integer.parseInt(in.next());
                if (size == 0) {
                    exit = true;
                    break;
                }
                if (size < MIN_BOARD_SIZE || size > MAX_BOARD_SIZE) {
                    throw new IllegalArgumentException("Board size value is outside the range");
                }
                break;
            } catch (IllegalArgumentException e) {
                //e.printStackTrace();
                out.println("Wrong value. Would you like to try again? (y/n)");
                exit = !in.next().equalsIgnoreCase("y");
            }
        }
        if (exit) {
            return false;
        }
        var pl1 = initPlayer("first");
        var pl2 = initPlayer("second");
        if (pl1.isPresent() && pl2.isPresent()) {
            player1 = pl1.get();
            player2 = pl2.get();
        } else {
            return false;
        }
        board = new GameBoard(size);
        rules = new GameRules(board);
        return true;
    }

    private Optional<Player> initPlayer(String num) {
        boolean goOn = true;
        int type = 1;
        while (goOn) {
            out.println("Input the " + num + " player type (1 - human, 2 - computer):");
            try {
                type = Integer.parseInt(in.next());
                if (type != 1 && type != 2) {
                    throw new IllegalArgumentException("Player type is outside the range");
                }
                break;
            } catch (IllegalArgumentException e) {
                //e.printStackTrace();
                out.println("Wrong value.Try again? (y/n)");
                if (!in.next().equalsIgnoreCase("y")) {
                    return Optional.empty();
                }
            }
        }
        String name = "";
        while (goOn) {
            out.println("Input the " + num + " player name:");
            try {
                name = in.next();
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Name is empty");
                }
                break;
            } catch (IllegalArgumentException e) {
                //e.printStackTrace();
                out.println("Wrong value. Try again? (y/n)");
                if (!in.next().equalsIgnoreCase("y")) {
                    return Optional.empty();
                }
            }
        }
        if (type == 1) {
            return Optional.of(new ConsolePlayer(name, in, out, boardView));
        } else {
            return Optional.of(new ComputerPlayer(out, boardView));
        }
    }
}

