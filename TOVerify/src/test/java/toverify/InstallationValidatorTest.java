
package toverify; // Places tests in the toverify package

import org.junit.jupiter.api.Test; // Provides the JUnit test annotation
import static org.junit.jupiter.api.Assertions.*; // Provides test assertions

import toverify.model.DocumentSection; // Represents document sections
import toverify.model.TechnicalOrder; // Represents a Technical Order
import toverify.model.ValidationReport; // Stores validation results
import toverify.validator.InstallationValidator; // Validates Installation TOs

public class InstallationValidatorTest { // Tests Installation validation rules

    @Test // Marks this method as a JUnit test
    void validInstallationTOShouldPass() { // Tests a complete Installation TO

        TechnicalOrder to = createValidTO(); // Creates a valid TO
        ValidationReport report = new ValidationReport(); // Creates a report

        new InstallationValidator().validate(to, report); // Runs validation

        assertTrue(report.isValid()); // Confirms validation passed
        assertEquals(0, report.getErrorCount()); // Confirms no errors
    }

    @Test // Marks this method as a JUnit test
    void missingChapterShouldFail() { // Tests a missing required chapter

        TechnicalOrder to = createValidTO(); // Creates a valid TO
        to.getSections().removeIf(section -> section.getNumber().equals("6")); // Removes Chapter 6

        ValidationReport report = new ValidationReport(); // Creates a report
        new InstallationValidator().validate(to, report); // Runs validation

        assertFalse(report.isValid()); // Confirms validation failed
        assertTrue(hasError(report, "INST-001")); // Confirms missing chapter error
    }

    @Test // Marks this method as a JUnit test
    void missingNetworkDiagramShouldFail() { // Tests missing required content

        TechnicalOrder to = createValidTO(); // Creates a valid TO
        to.getSections().removeIf(section -> section.getTitle().equals("NETWORK DIAGRAM")); // Removes diagram section

        ValidationReport report = new ValidationReport(); // Creates a report
        new InstallationValidator().validate(to, report); // Runs validation

        assertFalse(report.isValid()); // Confirms validation failed
        assertTrue(hasError(report, "INST-002")); // Confirms missing content error
    }

    @Test // Marks this method as a JUnit test
    void alternativeNumberingShouldPass() { // Tests section numbering flexibility

        TechnicalOrder to = createValidTO(); // Creates a valid TO

        to.getSections().removeIf(section -> section.getNumber().equals("1.3")); // Removes original diagram
        addSection(to, "1.8", "network diagram"); // Adds diagram with different number and case

        ValidationReport report = new ValidationReport(); // Creates a report
        new InstallationValidator().validate(to, report); // Runs validation

        assertTrue(report.isValid()); // Confirms title matching is case-insensitive
    }

    private TechnicalOrder createValidTO() { // Creates a complete sample TO

        TechnicalOrder to = new TechnicalOrder(); // Creates the TO object

        addSection(to, "1", "INTRODUCTION"); // Adds Chapter 1
        addSection(to, "1.1", "PURPOSE"); // Adds purpose
        addSection(to, "1.2", "SYSTEM DESCRIPTION"); // Adds description
        addSection(to, "1.3", "NETWORK DIAGRAM"); // Adds network diagram

        addSection(to, "2", "SYSTEM BASELINE"); // Adds Chapter 2
        addSection(to, "2.1", "HARDWARE"); // Adds hardware
        addSection(to, "2.2", "SOFTWARE"); // Adds software
        addSection(to, "2.3", "FIRMWARE"); // Adds firmware
        addSection(to, "2.4", "LOCATIONS"); // Adds locations

        addSection(to, "3", "INSTALLATION SETUP"); // Adds Chapter 3
        addSection(to, "3.1", "INSTALLATION REQUIREMENTS"); // Adds requirements
        addSection(to, "3.2", "PRE-INSTALLATION CHECK"); // Adds checks

        addSection(to, "4", "INSTALLATION"); // Adds Chapter 4
        addSection(to, "4.1", "INSTALL ROUTER"); // Adds installation procedure

        addSection(to, "5", "CONFIGURATION"); // Adds Chapter 5
        addSection(to, "5.1", "ROUTER CONFIGURATION"); // Adds configuration

        addSection(to, "6", "SECURITY"); // Adds Chapter 6
        addSection(to, "6.1", "SYSTEM HARDENING"); // Adds security

        return to; // Returns the complete TO
    }

    private void addSection(TechnicalOrder to, String number, String title) { // Adds a section

        int level = number.split("\\.").length; // Calculates hierarchy level
        to.addSection(new DocumentSection(number, title, level)); // Stores the section
    }

    private boolean hasError(ValidationReport report, String code) { // Searches for an error code

        return report.getErrors().stream() // Reads the validation errors
                .anyMatch(error -> error.getCode().equals(code)); // Checks for matching code
    }
}
