package com.example.tictactoe.consolegame.input;

import java.util.Optional;
import java.util.function.Predicate;

public interface ValidateInput<T> {
    Optional<T> ask(String msg, Predicate<T> predicate);
}
