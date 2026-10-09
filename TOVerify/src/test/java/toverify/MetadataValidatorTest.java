package toverify; // Places this test in the toverify package

import static org.junit.jupiter.api.Assertions.assertEquals; // Used to compare expected results
import static org.junit.jupiter.api.Assertions.assertFalse; // Used to check false results
import static org.junit.jupiter.api.Assertions.assertTrue; // Used to check true results

import org.junit.jupiter.api.Test; // Provides the JUnit test annotation

import toverify.model.TechnicalOrder; // Uses the Technical Order object
import toverify.model.ValidationReport; // Uses the validation report
import toverify.validator.MetadataValidator; // Uses the metadata validator

public class MetadataValidatorTest { // Tests Technical Order metadata validation

    @Test // Marks this method as a JUnit test
    public void testValidMetadata() { // Tests complete metadata

        TechnicalOrder technicalOrder = new TechnicalOrder(); // Creates a Technical Order
        technicalOrder.setToNumber("31W3-4-123-7"); // Sets the TO number
        technicalOrder.setTitle("Network Management System Installation"); // Sets the title
        technicalOrder.setClassification("UNCLASSIFIED"); // Sets the classification
        technicalOrder.setVersion("1.0"); // Sets the version

        ValidationReport report = new ValidationReport(); // Creates an empty report
        MetadataValidator validator = new MetadataValidator(); // Creates the validator

        validator.validate(technicalOrder, report); // Validates the metadata

        assertTrue(report.isValid()); // Checks that validation passed
        assertEquals(0, report.getErrorCount()); // Checks that no errors were found
    }

    @Test // Marks this method as a JUnit test
    public void testMissingTONumber() { // Tests a missing TO number

        TechnicalOrder technicalOrder = new TechnicalOrder(); // Creates a Technical Order
        technicalOrder.setTitle("Network Management System Installation"); // Sets the title
        technicalOrder.setClassification("UNCLASSIFIED"); // Sets the classification
        technicalOrder.setVersion("1.0"); // Sets the version

        ValidationReport report = new ValidationReport(); // Creates an empty report
        MetadataValidator validator = new MetadataValidator(); // Creates the validator

        validator.validate(technicalOrder, report); // Validates the metadata

        assertFalse(report.isValid()); // Checks that validation failed
        assertEquals(1, report.getErrorCount()); // Checks that one error was found
        assertEquals("META-001", report.getErrors().get(0).getCode()); // Checks the error code
    }

    @Test // Marks this method as a JUnit test
    public void testAllMetadataMissing() { // Tests completely missing metadata

        TechnicalOrder technicalOrder = new TechnicalOrder(); // Creates an empty Technical Order
        ValidationReport report = new ValidationReport(); // Creates an empty report
        MetadataValidator validator = new MetadataValidator(); // Creates the validator

        validator.validate(technicalOrder, report); // Validates the metadata

        assertFalse(report.isValid()); // Checks that validation failed
        assertEquals(4, report.getErrorCount()); // Checks that four errors were found
    }
}