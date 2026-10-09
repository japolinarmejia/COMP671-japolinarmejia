package toverify.validator; // Places this class in the validator package

import toverify.model.TechnicalOrder; // Uses the Technical Order object
import toverify.model.ValidationReport; // Uses the validation report

public class MetadataValidator { // Validates required TO metadata

    public void validate(TechnicalOrder technicalOrder, ValidationReport report) { // Validates metadata

        if (technicalOrder.getToNumber() == null || technicalOrder.getToNumber().isBlank()) { // Checks TO number
            report.addError("META-001", "Technical Order number is missing."); // Records missing TO number
        }

        if (technicalOrder.getTitle() == null || technicalOrder.getTitle().isBlank()) { // Checks title
            report.addError("META-002", "Technical Order title is missing."); // Records missing title
        }

        if (technicalOrder.getClassification() == null || technicalOrder.getClassification().isBlank()) { // Checks classification
            report.addError("META-003", "Technical Order classification is missing."); // Records missing classification
        }

        if (technicalOrder.getVersion() == null || technicalOrder.getVersion().isBlank()) { // Checks version
            report.addError("META-004", "Technical Order version is missing."); // Records missing version
        }
    }
}