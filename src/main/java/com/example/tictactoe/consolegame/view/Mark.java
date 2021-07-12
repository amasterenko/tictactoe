package com.example.tictactoe.consolegame.view;

import java.util.Map;

public interface Mark<S> {
    void show(S out, int playerNum);

    void setX(int playerNum);

    void setO(int playerNum);

    Map<Integer, Character> getPlayerSymbols();
}
