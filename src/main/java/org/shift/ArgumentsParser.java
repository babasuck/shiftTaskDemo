package org.shift;

import com.beust.jcommander.JCommander;

public class ArgumentsParser {
    public static Arguments getArguments(String ... argv) {
        Arguments args = new Arguments();
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);
        return args;
    }
}


