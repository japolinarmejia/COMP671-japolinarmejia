package toverify.validator; // Places this class in the validator package

import toverify.model.DocumentSection; // Uses document sections
import toverify.model.TechnicalOrder; // Uses the Technical Order object
import toverify.model.ValidationReport; // Uses the validation report

public class StructureValidator { // Validates the TO document structure

    private static final int MAX_LEVEL = 4; // Defines the maximum hierarchy level

    public void validate(TechnicalOrder technicalOrder, ValidationReport report) { // Validates TO structure

        for (DocumentSection section : technicalOrder.getSections()) { // Checks each section

            if (section.getLevel() > MAX_LEVEL) { // Checks for more than four levels

                report.addError( // Adds a structure error
                        "STR-001", // Identifies the structure requirement
                        "Section " + section.getNumber() + " exceeds four hierarchy levels."); // Describes the error
            }
        }
    }
}