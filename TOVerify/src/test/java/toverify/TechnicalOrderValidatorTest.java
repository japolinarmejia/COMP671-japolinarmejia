
package toverify; // Places tests in the toverify package

import org.junit.jupiter.api.Test; // Provides the JUnit test annotation
import static org.junit.jupiter.api.Assertions.*; // Provides test assertions

import toverify.model.TechnicalOrder; // Represents a Technical Order
import toverify.model.ValidationReport; // Stores validation results
import toverify.validator.TechnicalOrderValidator; // Coordinates validation

public class TechnicalOrderValidatorTest { // Tests TO type validation behavior

    @Test // Marks this method as a JUnit test
    void operationsTOShouldBeUnsupported() { // Tests Operations TO handling

        TechnicalOrder to = createTO("31W3-4-123-1"); // Creates an Operations TO
        ValidationReport report = new TechnicalOrderValidator().validate(to); // Runs validation

        assertFalse(report.isValid()); // Confirms the TO was not approved
        assertTrue(hasError(report, "TYPE-002")); // Checks unsupported Operations error
    }

    @Test // Marks this method as a JUnit test
    void maintenanceTOShouldBeUnsupported() { // Tests Maintenance TO handling

        TechnicalOrder to = createTO("31W3-4-123-2"); // Creates a Maintenance TO
        ValidationReport report = new TechnicalOrderValidator().validate(to); // Runs validation

        assertFalse(report.isValid()); // Confirms the TO was not approved
        assertTrue(hasError(report, "TYPE-003")); // Checks unsupported Maintenance error
    }

    @Test // Marks this method as a JUnit test
    void unknownTOShouldBeRejected() { // Tests an unrecognized TO type

        TechnicalOrder to = createTO("31W3-4-123-9"); // Creates an unknown TO type
        ValidationReport report = new TechnicalOrderValidator().validate(to); // Runs validation

        assertFalse(report.isValid()); // Confirms the TO was not approved
        assertTrue(hasError(report, "TYPE-004")); // Checks unknown type error
    }

    @Test // Marks this method as a JUnit test
    void missingTONumberShouldBeRejected() { // Tests missing TO number

        TechnicalOrder to = createTO(null); // Creates a TO without a number
        ValidationReport report = new TechnicalOrderValidator().validate(to); // Runs validation

        assertFalse(report.isValid()); // Confirms the TO was not approved
        assertTrue(hasError(report, "META-001")); // Checks missing metadata error
        assertTrue(hasError(report, "TYPE-004")); // Checks unknown type error
    }

    private TechnicalOrder createTO(String number) { // Creates a TO with valid metadata

        TechnicalOrder to = new TechnicalOrder(); // Creates the TO object
        to.setToNumber(number); // Sets the TO number
        to.setTitle("Test Technical Order"); // Sets the title
        to.setClassification("UNCLASSIFIED"); // Sets the classification
        to.setVersion("1.0"); // Sets the version

        return to; // Returns the TO
    }

    private boolean hasError(ValidationReport report, String code) { // Searches for an error code

        return report.getErrors().stream() // Reads validation errors
                .anyMatch(error -> error.getCode().equals(code)); // Checks the error code
    }
}
