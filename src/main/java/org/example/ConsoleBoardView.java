package org.example;

import java.io.PrintWriter;

public class ConsoleBoardView implements BoardView {
    private final Mark<PrintWriter> mark;
    private final PrintWriter out;

    public ConsoleBoardView(PrintWriter out, Mark<PrintWriter> mark) {
        this.mark = mark;
        this.out = out;
    }

    @Override
    public void show(int[][] board) {
        out.print(("   "));
        for (int i = 0; i < board.length; i++) {
            out.print(("  " + (i + 1) + " "));
        }
        out.println();
        writeLine(out, board.length);
        for (int i = 0; i < board.length; i++) {
            out.print(((i + 1) + "  |"));
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != 0) {
                    mark.show(out, board[i][j]);
                } else {
                    out.print("   ");
                }
                out.print("|");
            }
            out.println();
            writeLine(out, board.length);
        }
    }

    private void writeLine(PrintWriter out, int size) {
        out.print("   +");
        out.print("---+".repeat(size));
        out.println();
    }
}
