package toverify; // Places this test in the toverify package

import static org.junit.jupiter.api.Assertions.assertEquals; // Used to compare expected results

import org.junit.jupiter.api.Test; // Provides the JUnit test annotation

import toverify.model.TechnicalOrderType; // Uses the TO type values
import toverify.parser.TOTypeDetector; // Uses the TO type detector

public class TOTypeDetectorTest { // Tests Technical Order type detection

    @Test // Marks this method as a JUnit test
    public void testOperationsTO() { // Tests an Operations TO

        TOTypeDetector detector = new TOTypeDetector(); // Creates the detector

        TechnicalOrderType result = detector.detect("31W3-4-123-1"); // Detects the TO type

        assertEquals(TechnicalOrderType.OPERATIONS, result); // Checks for Operations
    }

    @Test // Marks this method as a JUnit test
    public void testMaintenanceTO() { // Tests a Maintenance TO

        TOTypeDetector detector = new TOTypeDetector(); // Creates the detector

        TechnicalOrderType result = detector.detect("31W3-4-123-2"); // Detects the TO type

        assertEquals(TechnicalOrderType.MAINTENANCE, result); // Checks for Maintenance
    }

    @Test // Marks this method as a JUnit test
    public void testInstallationTO() { // Tests an Installation TO

        TOTypeDetector detector = new TOTypeDetector(); // Creates the detector

        TechnicalOrderType result = detector.detect("31W3-4-123-7"); // Detects the TO type

        assertEquals(TechnicalOrderType.INSTALLATION, result); // Checks for Installation
    }

    @Test // Marks this method as a JUnit test
    public void testUnknownTO() { // Tests an unsupported TO type

        TOTypeDetector detector = new TOTypeDetector(); // Creates the detector

        TechnicalOrderType result = detector.detect("31W3-4-123-9"); // Detects the TO type

        assertEquals(TechnicalOrderType.UNKNOWN, result); // Checks for Unknown
    }
}