package com.example.tictactoe.consolegame;

import com.example.tictactoe.consolegame.init.ConsoleGameInit;
import com.example.tictactoe.logic.GameFlow;

public interface GameRounds {
    void play(ConsoleGameInit setUp, GameFlow gameFlow);
}
