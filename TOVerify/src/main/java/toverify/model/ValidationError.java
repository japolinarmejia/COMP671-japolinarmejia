package toverify.model; // Places this class in the model package

public class ValidationError { // Represents one validation problem

    private String code; // Stores the requirement or error code
    private String message; // Stores the error description

    public ValidationError(String code, String message) { // Creates a validation error
        this.code = code; // Saves the error code
        this.message = message; // Saves the error message
    }

    public String getCode() { // Gets the error code
        return code; // Returns the error code
    }

    public String getMessage() { // Gets the error message
        return message; // Returns the error message
    }

    @Override // Indicates that this replaces the default toString method
    public String toString() { // Creates readable error output
        return "[" + code + "] " + message; // Returns the formatted error
    }
}