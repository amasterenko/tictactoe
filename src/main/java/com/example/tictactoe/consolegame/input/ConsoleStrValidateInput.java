package com.example.tictactoe.consolegame.input;

import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;
/**
 * Class asks for a numeric input and validate it with the predicate.
 *
 * @author AndrewMs
 * @version 1.0
 */
public class ConsoleStrValidateInput implements ValidateInput<String> {
    private final Scanner in;
    private final PrintWriter out;

    public ConsoleStrValidateInput(Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public Optional<String> ask(String msg, Predicate<String> predicate) {
        String res = "";
        boolean goOn = true;
        while (goOn) {
            out.println(msg);
            res = in.nextLine();
            if (!predicate.test(res)) {
                out.println("Wrong value. Try again? (y/n)");
                goOn = in.nextLine().equalsIgnoreCase("y");
            } else {
                return Optional.of(res);
            }
        }
        return Optional.empty();
    }
}
