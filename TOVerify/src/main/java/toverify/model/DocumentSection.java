package toverify.model; // Places this class in the model package

public class DocumentSection { // Represents one section of the TO

    private String number; // Stores the section number
    private String title; // Stores the section title
    private int level; // Stores the hierarchy level
    private String content; // Stores the section content

    public DocumentSection(String number, String title, int level) { // Creates a section
        this.number = number; // Saves the section number
        this.title = title; // Saves the section title
        this.level = level; // Saves the hierarchy level
        this.content = ""; // Starts with empty content
    }

    public String getNumber() { // Gets the section number
        return number; // Returns the section number
    }

    public String getTitle() { // Gets the section title
        return title; // Returns the section title
    }

    public int getLevel() { // Gets the hierarchy level
        return level; // Returns the hierarchy level
    }

    public String getContent() { // Gets the section content
        return content; // Returns the section content
    }

    public void addContent(String line) { // Adds text to the section
        if (!content.isEmpty()) { // Checks if content already exists
            content += System.lineSeparator(); // Starts a new line
        }

        content += line; // Adds the new text
    }
}