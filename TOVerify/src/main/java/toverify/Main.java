
package toverify; // Main program package

import java.io.IOException; // Handles file reading errors
import java.nio.file.Path; // Represents file locations

import toverify.model.TechnicalOrder; // Uses the Technical Order model
import toverify.model.TechnicalOrderType; // Uses TO type values
import toverify.model.ValidationError; // Uses validation errors
import toverify.model.ValidationReport; // Uses validation results
import toverify.parser.TechnicalOrderParser; // Reads Technical Order files
import toverify.parser.TOTypeDetector; // Detects the TO type
import toverify.validator.TechnicalOrderValidator; // Validates Technical Orders

public class Main { // Starts the TOVerify application

    public static void main(String[] args) { // Main program entry point

        if (args.length == 0) { // Checks whether a filename was provided
            System.out.println("Usage: java toverify.Main <file-path>"); // Shows usage instructions
            return; // Stops when no file is provided
        }

        Path filePath = Path.of(args[0]); // Gets the filename from the command line

        TechnicalOrderParser parser = new TechnicalOrderParser(); // Creates the parser
        TechnicalOrderValidator validator = new TechnicalOrderValidator(); // Creates the validator
        TOTypeDetector detector = new TOTypeDetector(); // Creates the type detector

        try { // Starts file processing

            TechnicalOrder technicalOrder = parser.parse(filePath); // Reads the TO file
            TechnicalOrderType type = detector.detect(technicalOrder.getToNumber()); // Detects TO type
            ValidationReport report = validator.validate(technicalOrder); // Validates the TO

            System.out.println("TOVERIFY"); // Displays application name
            System.out.println("--------------------------------"); // Displays separator
            System.out.println("TO Number: " + technicalOrder.getToNumber()); // Displays TO number
            System.out.println("Title: " + technicalOrder.getTitle()); // Displays title
            System.out.println("Type: " + type); // Displays detected TO type
            System.out.println("Sections Found: " + technicalOrder.getSections().size()); // Displays section count
            System.out.println(); // Adds a blank line

            if (report.isValid()) { // Checks whether validation passed
                System.out.println("VALIDATION RESULT: PASSED"); // Displays passing result
            } else { // Handles failed validation
                System.out.println("VALIDATION RESULT: FAILED"); // Displays failing result
            }

            System.out.println("Errors Found: " + report.getErrorCount()); // Displays error count

            for (ValidationError error : report.getErrors()) { // Goes through validation errors
                System.out.println(error); // Displays each error code and message
            }

        } catch (IOException e) { // Handles file reading problems
            System.out.println("Error reading Technical Order file."); // Displays error message
            System.out.println(e.getMessage()); // Displays error details
        }
    }
}
