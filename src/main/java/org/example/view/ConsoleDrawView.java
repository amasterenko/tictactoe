package org.example.view;

import java.io.PrintWriter;

public class ConsoleDrawView implements DrawView {
    private final PrintWriter out;

    public ConsoleDrawView(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void show() {
        out.println("-----");
        out.println("Draw.");
        out.println("-----");
    }
}
