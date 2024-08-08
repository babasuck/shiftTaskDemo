import org.junit.jupiter.api.Test;
import org.shift.Arguments;
import org.shift.ArgumentsParser;
import org.shift.Solver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class Tester {

    private static Solver solver;

    @Test
    void testSolverWithDefaultArgsTemplateOne() {
        String[] args = {"-p", "result-", "./src/main/resources/in1.txt", "./src/main/resources/in2.txt"};
        solveWithArgs(args);
        checkTemplateOne();
        deleteResultFilesIfExist("result-");
    }

    @Test
    void should_NotCreateResultFiles_WhenFilesIsEmpty() {
        String[] args = {"-p", "result-", "./src/main/resources/in1empty.txt", "./src/main/resources/in2empty.txt"};
        deleteResultFilesIfExist("result-");

        solveWithArgs(args);

        Path resultFloat = Path.of("result-floats.txt");
        Path resultInt = Path.of("result-integer.txt");
        Path resultStrings = Path.of("result-strings.txt");

        assertFalse(Files.exists(resultFloat));
        assertFalse(Files.exists(resultInt));
        assertFalse(Files.exists(resultStrings));
    }

    @Test
    void testAppendMode() {
        String[] args = {"-f", "-a", "-p", "result-", "./src/main/resources/in1.txt", "./src/main/resources/in2.txt"};

        deleteResultFilesIfExist("result-");

        solveWithArgs(args);
        solveWithArgs(args);

        Path resultFloat = Path.of("result-floats.txt");
        Path resultInt = Path.of("result-integer.txt");
        Path resultStrings = Path.of("result-strings.txt");

        try {
            List<String> floatLines = Files.readAllLines(resultFloat);
            List<String> intLines = Files.readAllLines(resultInt);
            List<String> stringLines = Files.readAllLines(resultStrings);

            List<String> expectedFloatLines = new ArrayList<>(List.of("3.1415", "-0.001", "1.5285351E-25"));
            List<String> expectedIntLines =  new ArrayList<>(List.of("45", "100500", "1234567890123456789"));
            List<String> expectedStringLines =  new ArrayList<>(List.of(
                    "Lorem ipsum dolor sit amet",
                    "Пример",
                    "consectetur adipiscing",
                    "тестовое задание",
                    "Нормальная форма числа с плавающей запятой",
                    "Long"
            ));
            expectedFloatLines.addAll(expectedFloatLines);
            expectedIntLines.addAll(expectedIntLines);
            expectedStringLines.addAll(expectedStringLines);

            assertLinesMatch(expectedFloatLines, floatLines);
            assertLinesMatch(expectedIntLines, intLines);
            assertLinesMatch(expectedStringLines, stringLines);
        }
        catch (IOException e) {
            System.err.println("Error with files. " + e);
            fail();
        }
        deleteResultFilesIfExist("result-");
    }

    @Test
    void testOutputParam() {
        String appdata = System.getenv("appdata");
        String[] args = {"-o", appdata, "./src/main/resources/in1.txt", "./src/main/resources/in2.txt"};

        solveWithArgs(args);

        Path resultFloat = Path.of(appdata + "/result-floats.txt");
        Path resultInt = Path.of(appdata + "/result-integer.txt");
        Path resultStrings = Path.of(appdata + "/result-strings.txt");

        try {
            List<String> floatLines = Files.readAllLines(resultFloat);
            List<String> intLines = Files.readAllLines(resultInt);
            List<String> stringLines = Files.readAllLines(resultStrings);

            assertLinesMatch(floatLines, List.of("3.1415", "-0.001", "1.5285351E-25"));
            assertLinesMatch(intLines, List.of("45", "100500", "1234567890123456789"));
            assertLinesMatch(stringLines, List.of(
                    "Lorem ipsum dolor sit amet",
                    "Пример",
                    "consectetur adipiscing",
                    "тестовое задание",
                    "Нормальная форма числа с плавающей запятой",
                    "Long"
            ));
        }
        catch (IOException e) {
            System.err.println("Error with files. " + e);
            fail();
        }
        deleteResultFilesIfExist("result-");
    }



    private static Arguments makeArgumentsFromStringArray(String[] args) {
        return ArgumentsParser.getArguments(args);
    }

    private static Solver makeSolver(Arguments args) {
        return new Solver(args);
    }

    private static void deleteResultFilesIfExist(String prefix) {
        Path resultFloat = Path.of(prefix + "floats.txt");
        Path resultInt = Path.of(prefix + "integer.txt");
        Path resultStrings = Path.of(prefix + "strings.txt");
        try  {
            Files.delete(resultFloat);
            Files.delete(resultInt);
            Files.delete(resultStrings);
        }
        catch (IOException _) {
        }
    }

    private static void solveWithArgs(String ... args) {
        solver = makeSolver(makeArgumentsFromStringArray(args));
        try {
            solver.solve();
        } catch (IOException e) {
            System.err.println(e);
            fail();
        }
    }

    private static void checkTemplateOne() {
        Path resultFloat = Path.of("result-floats.txt");
        Path resultInt = Path.of("result-integer.txt");
        Path resultStrings = Path.of("result-strings.txt");

        try {
            List<String> floatLines = Files.readAllLines(resultFloat);
            List<String> intLines = Files.readAllLines(resultInt);
            List<String> stringLines = Files.readAllLines(resultStrings);

            assertLinesMatch(floatLines, List.of("3.1415", "-0.001", "1.5285351E-25"));
            assertLinesMatch(intLines, List.of("45", "100500", "1234567890123456789"));
            assertLinesMatch(stringLines, List.of(
                    "Lorem ipsum dolor sit amet",
                    "Пример",
                    "consectetur adipiscing",
                    "тестовое задание",
                    "Нормальная форма числа с плавающей запятой",
                    "Long"
            ));
        }
        catch (IOException e) {
            System.err.println("Error with files. " + e);
            fail();
        }
    }


}
