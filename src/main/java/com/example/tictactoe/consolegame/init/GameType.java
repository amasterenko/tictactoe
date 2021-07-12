package com.example.tictactoe.consolegame.init;

public enum GameType {
    HUMAN_HUMAN("New human-human game"),
    HUMAN_COMPUTER("New human-computer game"),
    COMPUTER_COMPUTER("New computer-computer game");

    private String name;

    GameType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
