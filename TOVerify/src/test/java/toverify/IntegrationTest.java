package toverify; // Places this test in the toverify package

import static org.junit.jupiter.api.Assertions.assertEquals; // Used to compare expected results
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue; // Used to check true results

import java.io.IOException; // Handles file reading errors
import java.nio.file.Path; // Represents the file path

import org.junit.jupiter.api.Test; // Provides the JUnit test annotation

import toverify.model.TechnicalOrder; // Uses the Technical Order object
import toverify.model.ValidationReport; // Uses the validation report
import toverify.parser.TechnicalOrderParser; // Uses the TO parser
import toverify.validator.TechnicalOrderValidator; // Uses the complete TO validator
import static org.junit.jupiter.api.Assertions.assertFalse; // Used to check false results

public class IntegrationTest { // Tests multiple TOVerify components together

    @Test // Marks this method as a JUnit test
    public void testValidInstallationTO() throws IOException { // Tests the valid sample from file to validation

        Path filePath = Path.of("samples", "valid_installation_to.txt"); // Selects the valid sample file

        TechnicalOrderParser parser = new TechnicalOrderParser(); // Creates the parser
        TechnicalOrder technicalOrder = parser.parse(filePath); // Parses the sample file

        TechnicalOrderValidator validator = new TechnicalOrderValidator(); // Creates the main validator
        ValidationReport report = validator.validate(technicalOrder); // Validates the parsed TO

        assertEquals("31W3-4-123-7", technicalOrder.getToNumber()); // Checks the parsed TO number
        assertEquals(28, technicalOrder.getSections().size()); // Checks the parsed section count
        assertTrue(report.isValid()); // Checks that the complete TO passes validation
        assertEquals(0, report.getErrorCount()); // Checks that no validation errors exist
    }

    @Test // Marks this method as a JUnit test
    public void testInvalidMetadataTO() throws IOException { // Tests a TO with missing metadata

        Path filePath = Path.of("samples", "invalid_metadata_to.txt"); // Selects the invalid sample file

        TechnicalOrderParser parser = new TechnicalOrderParser(); // Creates the parser
        TechnicalOrder technicalOrder = parser.parse(filePath); // Parses the invalid sample file

        TechnicalOrderValidator validator = new TechnicalOrderValidator(); // Creates the main validator
        ValidationReport report = validator.validate(technicalOrder); // Validates the parsed TO

        assertFalse(report.isValid()); // Checks that the TO fails validation
        assertEquals(1, report.getErrorCount()); // Checks that exactly one error was found
        assertEquals("META-003", report.getErrors().get(0).getCode()); // Checks for missing classification
    }

    @Test // Marks this method as a JUnit test
    public void testInvalidStructureTO() throws IOException { // Tests a TO with an invalid hierarchy

        Path filePath = Path.of("samples", "invalid_structure_to.txt"); // Selects the invalid structure file

        TechnicalOrderParser parser = new TechnicalOrderParser(); // Creates the parser
        TechnicalOrder technicalOrder = parser.parse(filePath); // Parses the invalid sample file

        TechnicalOrderValidator validator = new TechnicalOrderValidator(); // Creates the main validator
        ValidationReport report = validator.validate(technicalOrder); // Validates the parsed TO

        assertFalse(report.isValid()); // Checks that the TO fails validation
        assertEquals(1, report.getErrorCount()); // Checks that exactly one error was found
        assertEquals("STR-001", report.getErrors().get(0).getCode()); // Checks the hierarchy error
    }

    @Test // Marks this method as a JUnit test
    public void testInvalidContentTO() throws IOException { // Tests missing Installation TO content

        Path filePath = Path.of("samples", "invalid_content_to.txt"); // Selects invalid content file

        TechnicalOrderParser parser = new TechnicalOrderParser(); // Creates the parser
        TechnicalOrder technicalOrder = parser.parse(filePath); // Parses the sample file

        TechnicalOrderValidator validator = new TechnicalOrderValidator(); // Creates the main validator
        ValidationReport report = validator.validate(technicalOrder); // Validates the parsed TO

        assertFalse(report.isValid()); // Checks that validation fails
        assertEquals(1, report.getErrorCount()); // Checks that exactly one error exists
        assertEquals("INST-002", report.getErrors().get(0).getCode()); // Checks missing content error
        assertEquals("Chapter 1 is missing required section: NETWORK DIAGRAM.",
                report.getErrors().get(0).getMessage()); // Checks the error description
    }

}