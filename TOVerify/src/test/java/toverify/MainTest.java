
package toverify; // Places tests in the toverify package

import static org.junit.jupiter.api.Assertions.*; // Provides JUnit assertions

import java.io.ByteArrayOutputStream; // Captures console output
import java.io.PrintStream; // Redirects console output
import java.nio.charset.StandardCharsets; // Provides UTF-8 encoding

import org.junit.jupiter.api.Test; // Identifies JUnit tests

public class MainTest { // Tests the command-line application

    private String runMain(String... args) { // Runs Main and captures its output

        PrintStream originalOut = System.out; // Saves the original console
        ByteArrayOutputStream output = new ByteArrayOutputStream(); // Stores program output

        try { // Ensures console output is restored

            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8)); // Redirects output
            Main.main(args); // Runs the application

        } finally { // Executes even if the test fails

            System.setOut(originalOut); // Restores the original console
        }

        return output.toString(StandardCharsets.UTF_8); // Returns captured output
    }

    @Test // Marks this method as a JUnit test
    void validTOShouldPass() { // Tests a valid Installation TO

        String output = runMain("samples/valid_installation_to.txt"); // Runs valid sample

        assertTrue(output.contains("Type: INSTALLATION")); // Checks detected TO type
        assertTrue(output.contains("VALIDATION RESULT: PASSED")); // Checks passing result
        assertTrue(output.contains("Errors Found: 0")); // Checks error count
    }

    @Test // Marks this method as a JUnit test
    void invalidTOShouldFail() { // Tests an invalid Installation TO

        String output = runMain("samples/invalid_content_to.txt"); // Runs invalid sample

        assertTrue(output.contains("VALIDATION RESULT: FAILED")); // Checks failing result
        assertTrue(output.contains("[INST-002]")); // Checks required content error
    }

    @Test // Marks this method as a JUnit test
    void missingArgumentShouldShowUsage() { // Tests missing command-line input

        String output = runMain(); // Runs without a filename

        assertTrue(output.contains("Usage:")); // Checks usage instructions
    }

    @Test // Marks this method as a JUnit test
    void missingFileShouldShowError() { // Tests a nonexistent input file

        String output = runMain("samples/file_does_not_exist.txt"); // Runs missing file

        assertTrue(output.contains("Error reading Technical Order file.")); // Checks error message
    }
}
