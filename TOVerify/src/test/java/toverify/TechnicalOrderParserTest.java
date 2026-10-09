package toverify; // Places this test in the toverify package

import static org.junit.jupiter.api.Assertions.assertEquals; // Used to compare expected results

import java.io.IOException; // Handles file reading errors
import java.nio.file.Path; // Represents the file path

import org.junit.jupiter.api.Test; // Provides the JUnit test annotation

import toverify.model.TechnicalOrder; // Uses the Technical Order object
import toverify.parser.TechnicalOrderParser; // Uses the TO parser

public class TechnicalOrderParserTest { // Tests the Technical Order parser

    @Test // Marks this method as a JUnit test
    public void testValidTechnicalOrderMetadata() throws IOException { // Tests TO metadata

        Path filePath = Path.of("samples", "valid_installation_to.txt"); // Selects the sample file

        TechnicalOrderParser parser = new TechnicalOrderParser(); // Creates the parser

        TechnicalOrder technicalOrder = parser.parse(filePath); // Parses the sample file

        assertEquals("31W3-4-123-7", technicalOrder.getToNumber()); // Checks TO number
        assertEquals("Network Management System Installation", technicalOrder.getTitle()); // Checks title
        assertEquals("UNCLASSIFIED", technicalOrder.getClassification()); // Checks classification
        assertEquals("1.0", technicalOrder.getVersion()); // Checks TO version
    }

    @Test // Marks this method as a JUnit test
    public void testTechnicalOrderSections() throws IOException { // Tests section parsing

    Path filePath = Path.of("samples", "valid_installation_to.txt"); // Selects the sample file

        TechnicalOrderParser parser = new TechnicalOrderParser(); // Creates the parser

        TechnicalOrder technicalOrder = parser.parse(filePath); // Parses the sample file

        assertEquals(28, technicalOrder.getSections().size()); // Checks the total section count
        assertEquals("1", technicalOrder.getSections().get(0).getNumber()); // Checks first section number
        assertEquals("INTRODUCTION", technicalOrder.getSections().get(0).getTitle()); // Checks first section title
        assertEquals(1, technicalOrder.getSections().get(0).getLevel()); // Checks first section level

        assertEquals("4.1.1.1", technicalOrder.getSections().get(21).getNumber()); // Checks level four section
        assertEquals(4, technicalOrder.getSections().get(21).getLevel()); // Checks maximum valid level
    }
}
