package org.example;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ComputerPlayer implements Player {
    private static int num = 1;
    private final String name = "ComputerPlayer" + num++;
    private final BoardView boardView;
    private final PrintWriter out;

    public ComputerPlayer(PrintWriter out, BoardView boardView) {
        this.boardView = boardView;
        this.out = out;
    }

    @Override
    public int[] makeTurn(int[][] boardState, int curPlayerNum, boolean repeated) {
        boardView.show(boardState);
        out.println((name + " turns:"));
        Map<Integer, Map<Integer, Integer>> cells = new HashMap<>();
        int count = 0;
        for (int i = 0; i < boardState.length; i++) { //y
            for (int j = 0; j < boardState.length; j++) { //x
                if (boardState[i][j] == 0) {
                    cells.put(++count, Map.of(j, i)); //x,y
                }
            }
        }
        int[] res = new int[2];
        int indx = (int) ((Math.random() * (count - 1)) + 1);
        cells.get(indx).forEach((key, value) -> {
            res[0] = key + 1;
            res[1] = value + 1;
        });
        return res;
    }

    @Override
    public String getName() {
        return name;
    }
}
