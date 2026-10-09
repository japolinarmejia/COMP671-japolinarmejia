package toverify; // Places this test in the toverify package

import static org.junit.jupiter.api.Assertions.assertEquals; // Used to compare expected results
import static org.junit.jupiter.api.Assertions.assertFalse; // Used to check false results
import static org.junit.jupiter.api.Assertions.assertTrue; // Used to check true results

import org.junit.jupiter.api.Test; // Provides the JUnit test annotation

import toverify.model.DocumentSection; // Uses document sections
import toverify.model.TechnicalOrder; // Uses the Technical Order object
import toverify.model.ValidationReport; // Uses the validation report
import toverify.validator.StructureValidator; // Uses the structure validator

public class StructureValidatorTest { // Tests Technical Order structure validation

    @Test // Marks this method as a JUnit test
    public void testLevelFourIsValid() { // Tests the maximum valid hierarchy level

        TechnicalOrder technicalOrder = new TechnicalOrder(); // Creates a Technical Order

        technicalOrder.addSection( // Adds a level four section
                new DocumentSection("1.1.1.1", "VALID LEVEL FOUR", 4)); // Creates the section

        ValidationReport report = new ValidationReport(); // Creates an empty report
        StructureValidator validator = new StructureValidator(); // Creates the validator

        validator.validate(technicalOrder, report); // Validates the structure

        assertTrue(report.isValid()); // Checks that level four passes
        assertEquals(0, report.getErrorCount()); // Checks that no errors were found
    }

    @Test // Marks this method as a JUnit test
    public void testLevelFiveIsInvalid() { // Tests a hierarchy that is too deep

        TechnicalOrder technicalOrder = new TechnicalOrder(); // Creates a Technical Order

        technicalOrder.addSection( // Adds a level five section
                new DocumentSection("1.1.1.1.1", "INVALID LEVEL FIVE", 5)); // Creates the section

        ValidationReport report = new ValidationReport(); // Creates an empty report
        StructureValidator validator = new StructureValidator(); // Creates the validator

        validator.validate(technicalOrder, report); // Validates the structure

        assertFalse(report.isValid()); // Checks that level five fails
        assertEquals(1, report.getErrorCount()); // Checks that one error was found
        assertEquals("STR-001", report.getErrors().get(0).getCode()); // Checks the error code
    }
}
