package com.example.tictactoe.consolegame.input;

import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;
/**
 * Class asks for a string input and validate it with the predicate.
 *
 * @author AndrewMs
 * @version 1.0
 */
public class ConsoleIntValidateInput implements ValidateInput<Integer> {
    private final Scanner in;
    private final PrintWriter out;

    public ConsoleIntValidateInput(Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public Optional<Integer> ask(String msg, Predicate<Integer> predicate) {
        int res = 0;
        boolean goOn = true;
        while (goOn) {
            out.println(msg);
            try {
                res = Integer.parseInt(in.nextLine());
                if (!predicate.test(res)) {
                    throw new IllegalArgumentException(msg + " - wrong value!");
                }
                return Optional.of(res);
            } catch (IllegalArgumentException e) {
                out.println("Wrong value. Try again? (y/n)");
                goOn = in.nextLine().equalsIgnoreCase("y");
            }
        }
        return Optional.empty();
    }
}
