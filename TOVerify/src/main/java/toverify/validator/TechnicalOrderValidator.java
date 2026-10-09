
package toverify.validator; // Places this class in the validator package

import toverify.model.TechnicalOrder; // Uses the Technical Order object
import toverify.model.TechnicalOrderType; // Uses the TO type values
import toverify.model.ValidationReport; // Uses the validation report
import toverify.parser.TOTypeDetector; // Uses the TO type detector

public class TechnicalOrderValidator { // Coordinates all TO validation

    public ValidationReport validate(TechnicalOrder technicalOrder) { // Validates a Technical Order

        ValidationReport report = new ValidationReport(); // Creates the validation report

        MetadataValidator metadataValidator = new MetadataValidator(); // Creates metadata validator
        StructureValidator structureValidator = new StructureValidator(); // Creates structure validator
        InstallationValidator installationValidator = new InstallationValidator(); // Creates Installation validator
        TOTypeDetector typeDetector = new TOTypeDetector(); // Creates the TO type detector

        metadataValidator.validate(technicalOrder, report); // Validates required metadata
        structureValidator.validate(technicalOrder, report); // Validates document structure

        TechnicalOrderType type = typeDetector.detect(technicalOrder.getToNumber()); // Determines TO type

        if (type == TechnicalOrderType.INSTALLATION) { // Checks for an Installation TO

            installationValidator.validate(technicalOrder, report); // Validates Installation requirements

        } else if (type == TechnicalOrderType.OPERATIONS) { // Checks for an Operations TO

            report.addError("TYPE-002", "Operations TO validation is not supported in version 1.0."); // Reports unsupported type

        } else if (type == TechnicalOrderType.MAINTENANCE) { // Checks for a Maintenance TO

            report.addError("TYPE-003", "Maintenance TO validation is not supported in version 1.0."); // Reports unsupported type

        } else { // Handles an unknown or missing TO number

            report.addError("TYPE-004", "Technical Order type is not recognized."); // Reports unknown type
        }

        return report; // Returns the completed validation report
    }
}
