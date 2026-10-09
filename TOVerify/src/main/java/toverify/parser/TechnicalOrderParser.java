package toverify.parser; // Places this class in the parser package

import java.io.IOException; // Handles file reading errors
import java.nio.file.Files; // Provides methods to read files
import java.nio.file.Path; // Represents the file location
import java.util.List; // Stores the lines read from the file
import java.util.regex.Matcher; // Checks text against a pattern
import java.util.regex.Pattern; // Defines the section pattern

import toverify.model.DocumentSection; // Uses the DocumentSection class
import toverify.model.TechnicalOrder; // Uses the TechnicalOrder class

public class TechnicalOrderParser { // Reads a text file and creates a TO object

    private static final Pattern SECTION_PATTERN = // Defines a section heading pattern
            Pattern.compile("^(\\d+(?:\\.\\d+)*)\\.?\\s+(.+)$"); // Matches number and title

    public TechnicalOrder parse(Path filePath) throws IOException { // Parses a TO text file

        List<String> lines = Files.readAllLines(filePath); // Reads all lines from the file
        TechnicalOrder technicalOrder = new TechnicalOrder(); // Creates an empty TO object
        DocumentSection currentSection = null; // Tracks the current section

        for (String line : lines) { // Goes through each line in the file

            line = line.trim(); // Removes extra spaces

            if (line.isEmpty()) { // Checks for a blank line
                continue; // Skips blank lines
            }

            if (line.startsWith("TO:")) { // Checks for the TO number
                technicalOrder.setToNumber(line.substring(3).trim()); // Saves the TO number
                continue; // Moves to the next line
            }

            if (line.startsWith("TITLE:")) { // Checks for the title
                technicalOrder.setTitle(line.substring(6).trim()); // Saves the title
                continue; // Moves to the next line
            }

            if (line.startsWith("CLASSIFICATION:")) { // Checks for classification
                technicalOrder.setClassification(line.substring(15).trim()); // Saves classification
                continue; // Moves to the next line
            }

            if (line.startsWith("VERSION:") && currentSection == null) { // Checks for the TO version
                technicalOrder.setVersion(line.substring(8).trim()); // Saves the TO version
                continue; // Moves to the next line
            }

            Matcher matcher = SECTION_PATTERN.matcher(line); // Checks for a section heading

            if (matcher.matches()) { // Checks if the line is a section

                String number = matcher.group(1); // Gets the section number
                String title = matcher.group(2); // Gets the section title
                int level = number.split("\\.").length; // Calculates the section level

                currentSection = new DocumentSection(number, title, level); // Creates the section
                technicalOrder.addSection(currentSection); // Adds section to the TO
                continue; // Moves to the next line
            }

            if (currentSection != null) { // Checks if a section is active
                currentSection.addContent(line); // Adds text to the current section
            }
        }

        return technicalOrder; // Returns the completed TO object
    }
}