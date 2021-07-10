package org.example;

public class GameBoard implements Board {
    private final int[][] board;
    private final int size;
    private int freeCells;

    public GameBoard(int boardSize) {
        this.board = new int[boardSize][boardSize];
        this.size = boardSize;
        this.freeCells = boardSize * boardSize;
    }

    @Override
    public boolean takeTurn(int[] coordinates, int playerNum) {
        if (coordinates[0] < 1 || coordinates[0] > size || coordinates[1] < 1 || coordinates[1] > size) {
            return false;
        }
        if (board[coordinates[1] - 1][coordinates[0] - 1] != 0) {
            return false;
        }
        board[coordinates[1] - 1][coordinates[0] - 1] = playerNum;
        freeCells--;
        return true;
    }

    @Override
    public boolean isCompleted() {
        return freeCells == 0;
    }

    @Override
    public boolean hasCompletedLine() {
        return checkHorizontalLines() || checkVerticalLines() || checkDiagonalLines();
    }

    @Override
    public int[][] getState() {
        return board;
    }

    @Override
    public void reset() {
        freeCells = size * size;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = 0;
            }
        }
    }

    private boolean checkHorizontalLines() {
        boolean rsl = false;
        for (int i = 0; i < size; i++) {
            for (int j = 1; j < size; j++) {
                rsl = board[i][j] == board[i][0] && board[i][0] != 0;
                if (!rsl) {
                    break;
                }
            }
            if (rsl) {
                break;
            }
        }
        return rsl;
    }

    /**
     *
     * @return true if the board has at least one filled vertical line
     */

    private boolean checkVerticalLines() {
        boolean rsl = false;
        for (int i = 0; i < size; i++) {
            for (int j = 1; j < size; j++) {
                rsl = board[j][i] == board[0][i] && board[0][i] != 0;
                if (!rsl) {
                    break;
                }
            }
            if (rsl) {
                break;
            }
        }
        return rsl;
    }

    /**
     *
     * @return true if the board has at least one filled diagonal
     */

    private boolean checkDiagonalLines() {
        boolean rsl = false;
        for (int i = 1; i < size; i++) {
            rsl = board[i][i] == board[0][0] && board[0][0] != 0;
            if (!rsl) {
                break;
            }
        }
        if (rsl) {
            return true;
        }
        for (int i = 1; i < size; i++) {
            rsl = board[size - 1 - i][i] == board[size - 1][0] && board[size - 1][0] != 0;
            if (!rsl) {
                break;
            }
        }
        return rsl;
    }
}
