package org.shift;

/**
 * @author - bidanocka@gmail.com
 */

public class Main {
    public static void main(String... argv) {
        Arguments args = ArgumentsParser.getArguments(argv);
        Solver solver = new Solver(args);
        solver.solve();
        System.out.println("Task completed successfully.");
        solver.printStatistics();
    }
}

