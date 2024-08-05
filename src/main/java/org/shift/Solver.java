package org.shift;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class Solver {

    private final Arguments arguments;
    /**
     * Paths to output files
     */
    private Path intsPath;
    private Path floatsPath;
    private Path stringsPath;

    private Statistics stats;
    /**
     * Count of each types in files
     */
    private int intsCount = 0, floatsCount = 0, stringsCount = 0;

    /**
     * Place holders for data
     */
    List<Long> intsData = new LinkedList<>();
    List<Float> floatsData = new LinkedList<>();
    List<String> stringsData = new LinkedList<>();

    public Solver(Arguments args) {
        this.arguments = args;
        String outPath = arguments.getOutPath();
        try {
            this.intsPath = Path.of(outPath + String.format("%s%s", arguments.getOutputFilesPrefix(), "integer.txt"));
            this.floatsPath = Path.of(outPath + String.format("%s%s", arguments.getOutputFilesPrefix(), "floats.txt"));
            this.stringsPath = Path.of(outPath + String.format("%s%s", arguments.getOutputFilesPrefix(), "strings.txt"));
        }
        catch (InvalidPathException e) {
            System.err.println("Invalid prefix for output files.");
            System.exit(-1);
        }
    }

    public static void validateData(Arguments args) throws IllegalArgumentException{

    }

    /**
     * Main method to complete the task.
     */
    public void solve() {
        for(var file : arguments.getFiles()) {
            Path filePath = Path.of(file);
            try (var reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
                List<String> lines = reader.lines().toList();
                for(var line : lines) {
                    if(isInt(line)) {
                        intsData.add(Long.valueOf(line));
                    }
                    else if (isFloat(line)) {
                        floatsData.add(Float.valueOf(line));
                    }
                    else {
                        stringsData.add(line);
                    }
                }
                TaskFileWriter writer = new TaskFileWriter(arguments.isAppend());
                writer.writeDataLine(intsPath, intsData);
                writer.writeDataLine(floatsPath, floatsData);
                writer.writeDataLine(stringsPath, stringsData);
                if (arguments.isFullStat()) {
                    long intMin = 0, intMax = 0;
                    float floatMin = 0.0f, floatMax = 0.0f;
                    int stringShortest = 0, stringTallest = 0;
                    if (!intsData.isEmpty()) {
                        intMin = Collections.min(intsData);
                        intMax = Collections.max(intsData);
                    }
                    if (!floatsData.isEmpty()) {
                        floatMin = Collections.min(floatsData);
                        floatMax = Collections.max(floatsData);
                    }
                    if (!stringsData.isEmpty()) {
                        stringShortest = Collections.min(stringsData, Comparator.comparingInt(String::length)).length();
                        stringTallest = Collections.max(stringsData, Comparator.comparingInt(String::length)).length();
                    }
                     stats = new Statistics(intMin, intMax, floatMin, floatMax, stringShortest, stringTallest);
                }
            }
            catch (NoSuchFileException e) {
                System.err.println("Error while handling " + filePath + ", file not found.");
            }
            catch (AccessDeniedException e) {
                System.err.println("Error while handling " + filePath + ", Access Denied.");
            }
            catch (IOException e) {
                System.err.println("Error while handling " + filePath + " " + e);
            }
        }
    }
    private static boolean isInt(String s) {
        try {
            Long.parseLong(s);
            return true;
        }
        catch (NumberFormatException _) {
            return false;
        }
    }

    private static boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        }
        catch (NumberFormatException _) {
            return false;
        }
    }

    private record Statistics(long minInt,
                              long maxInt,
                              float minFloat,
                              float maxFloat,
                              long shortestString,
                              long tallestString) {
        @Override
        public String toString() {
            return "Statistics : " +
                    "\nminInt=" + minInt +
                    "\nmaxInt=" + maxInt +
                    "\nminFloat=" + minFloat +
                    "\nmaxFloat=" + maxFloat +
                    "\nshortestString=" + shortestString +
                    "\ntallestString=" + tallestString;
        }
    }

    public void printStatistics() {
        if(arguments.isFullStat() && stats != null) {
            System.out.println(stats);
        }
        else if(arguments.isSmallStat()){
            System.out.format("Integers: %d\nFloats: %d\nStrings: %d",
                    intsData.size(),
                    floatsData.size(),
                    stringsData.size());
        }
    }

}



