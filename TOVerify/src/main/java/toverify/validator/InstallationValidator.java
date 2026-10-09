
package toverify.validator; // Places this class in the validator package

import toverify.model.DocumentSection; // Uses document sections
import toverify.model.TechnicalOrder; // Uses the Technical Order object
import toverify.model.ValidationReport; // Uses the validation report

public class InstallationValidator { // Validates Installation TO requirements

    public void validate(TechnicalOrder technicalOrder, ValidationReport report) { // Starts validation

        for (int chapter = 1; chapter <= 6; chapter++) { // Checks Chapters 1 through 6

            if (!hasSection(technicalOrder, String.valueOf(chapter))) { // Checks for a missing chapter
                report.addError("INST-001", "Required Chapter " + chapter + " is missing."); // Records the error
            }
        }

        checkRequiredSection(technicalOrder, report, 1, "PURPOSE"); // Checks Chapter 1 purpose
        checkRequiredSection(technicalOrder, report, 1, "SYSTEM DESCRIPTION"); // Checks system description
        checkRequiredSection(technicalOrder, report, 1, "NETWORK DIAGRAM"); // Checks network diagram

        checkRequiredSection(technicalOrder, report, 2, "HARDWARE"); // Checks hardware baseline
        checkRequiredSection(technicalOrder, report, 2, "SOFTWARE"); // Checks software baseline
        checkRequiredSection(technicalOrder, report, 2, "FIRMWARE"); // Checks firmware baseline
        checkRequiredSection(technicalOrder, report, 2, "LOCATIONS"); // Checks installation locations

        checkRequiredSection(technicalOrder, report, 3, "INSTALLATION REQUIREMENTS"); // Checks setup requirements
        checkRequiredSection(technicalOrder, report, 3, "PRE-INSTALLATION CHECK"); // Checks pre-installation

        checkRequiredSection(technicalOrder, report, 4, "INSTALL ROUTER"); // Checks installation procedure
        checkRequiredSection(technicalOrder, report, 5, "ROUTER CONFIGURATION"); // Checks configuration procedure
        checkRequiredSection(technicalOrder, report, 6, "SYSTEM HARDENING"); // Checks security procedures
    }

    private void checkRequiredSection(TechnicalOrder technicalOrder, ValidationReport report,
                                      int chapter, String title) { // Checks a required section

        if (!hasSection(technicalOrder, String.valueOf(chapter))) { // Checks whether chapter exists
            return; // Avoids duplicate errors for missing chapters
        }

        for (DocumentSection section : technicalOrder.getSections()) { // Searches all sections

            if (section.getNumber().startsWith(chapter + ".") // Checks chapter membership
                    && section.getTitle().equalsIgnoreCase(title)) { // Checks the section title
                return; // Required section was found
            }
        }

        report.addError("INST-002", // Identifies a missing required section
                "Chapter " + chapter + " is missing required section: " + title + "."); // Describes error
    }

    private boolean hasSection(TechnicalOrder technicalOrder, String number) { // Finds a section number

        for (DocumentSection section : technicalOrder.getSections()) { // Searches all sections

            if (section.getNumber().equals(number)) { // Checks for matching section number
                return true; // Section exists
            }
        }

        return false; // Section was not found
    }
}
