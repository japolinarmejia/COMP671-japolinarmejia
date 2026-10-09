package toverify.parser; // Places this class in the parser package

import toverify.model.TechnicalOrderType; // Uses the TO type values

public class TOTypeDetector { // Determines the type of Technical Order

    public TechnicalOrderType detect(String toNumber) { // Detects type from the TO number

        if (toNumber == null || toNumber.isBlank()) { // Checks for a missing TO number
            return TechnicalOrderType.UNKNOWN; // Returns unknown for missing numbers
        }

        if (toNumber.endsWith("-1")) { // Checks for an Operations TO
            return TechnicalOrderType.OPERATIONS; // Returns Operations type
        }

        if (toNumber.endsWith("-2")) { // Checks for a Maintenance TO
            return TechnicalOrderType.MAINTENANCE; // Returns Maintenance type
        }

        if (toNumber.endsWith("-7")) { // Checks for an Installation TO
            return TechnicalOrderType.INSTALLATION; // Returns Installation type
        }

        return TechnicalOrderType.UNKNOWN; // Returns unknown for other TO numbers
    }
}