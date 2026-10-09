package toverify.model; // Places this class in the model package

import java.util.ArrayList; // Used to create the section list
import java.util.List; // Used to store multiple sections

public class TechnicalOrder { // Represents one Technical Order

    private String toNumber; // Stores the TO number
    private String title; // Stores the TO title
    private String classification; // Stores the classification level
    private String version; // Stores the document version

    private final List<DocumentSection> sections; // Stores all TO sections

    public TechnicalOrder() { // Creates a new Technical Order
        this.sections = new ArrayList<>(); // Creates an empty section list
    }

    public String getToNumber() { // Gets the TO number
        return toNumber; // Returns the TO number
    }

    public void setToNumber(String toNumber) { // Sets the TO number
        this.toNumber = toNumber; // Saves the TO number
    }

    public String getTitle() { // Gets the title
        return title; // Returns the title
    }

    public void setTitle(String title) { // Sets the title
        this.title = title; // Saves the title
    }

    public String getClassification() { // Gets the classification
        return classification; // Returns the classification
    }

    public void setClassification(String classification) { // Sets the classification
        this.classification = classification; // Saves the classification
    }

    public String getVersion() { // Gets the version
        return version; // Returns the version
    }

    public void setVersion(String version) { // Sets the version
        this.version = version; // Saves the version
    }

    public List<DocumentSection> getSections() { // Gets all document sections
        return sections; // Returns the section list
    }

    public void addSection(DocumentSection section) { // Adds a section to the TO
        sections.add(section); // Adds it to the section list
    }
}