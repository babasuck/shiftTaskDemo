package org.shift;

import com.beust.jcommander.*;
import java.util.*;

public class Arguments {
    @Parameter(names = {"-o", "-output"}, description = "Filtered files output path.")
    private String outPath = "";

    @Parameter(names = {"-p", "-prefix"}, description = "Output files prefix.")
    private String outputFilesPrefix = "result-";

    @Parameter(names = {"-a", "-append"}, description = "Append mode.")
    private boolean isAppend = false;

    @Parameter(names = {"-s", "-small"}, description = "Print small statistics.")
    private boolean smallStat = false;

    @Parameter(names = {"-f", "-full"}, description = "Print verbose statistics.")
    private boolean fullStat = false;

    @Parameter(description = "Files")
    private List<String> files = new ArrayList<>();

    public String getOutPath() {
        return outPath;
    }

    public String getOutputFilesPrefix() {
        return outputFilesPrefix;
    }

    public boolean isAppend() {
        return isAppend;
    }

    public boolean isSmallStat() {
        return smallStat;
    }

    public boolean isFullStat() {
        return fullStat;
    }

    public List<String> getFiles() {
        return files;
    }

    @Override
    public String toString() {
        return "Arguments{" +
                "outPath='" + outPath + '\'' +
                ", outputFilesPrefix='" + outputFilesPrefix + '\'' +
                ", isAppend=" + isAppend +
                ", smallStat=" + smallStat +
                ", fullStat=" + fullStat +
                ", files=" + files +
                '}';
    }
}
