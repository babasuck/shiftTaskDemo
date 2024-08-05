package org.shift;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class TaskFileWriter {

    /**
     * Check if -append option is applied
     */
    private StandardOpenOption[] openOptions;
    private BufferedWriter writer;

    public TaskFileWriter(boolean isAppend) {
        if (isAppend) {
            this.openOptions = new StandardOpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.APPEND};
        } else {
            this.openOptions = new StandardOpenOption[]{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING};
        }

    }

    public void writeDataLine(Path path, List<?> lines) throws IOException {
        if(lines.isEmpty())
            return;
        try(BufferedWriter writer = Files.newBufferedWriter(path, openOptions)) {
            for(var line : lines) {
                writer.write(Objects.toString(line));
                writer.newLine();
            }
        }
    }

}
