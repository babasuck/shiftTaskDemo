package org.shift;

import java.io.IOException;

/**
 * @author - bidanocka@gmail.com
 */

public class Main {
    public static void main(String... argv) {
        Arguments args = ArgumentsParser.getArguments(argv);
        Solver solver = new Solver(args);
        try {
            solver.solve();
        }
        catch (IOException e) {
            System.err.println("Error while handling file: " + e);
            return;
        }
        System.out.println("Task completed successfully.");
        solver.printStatistics();
    }
}

