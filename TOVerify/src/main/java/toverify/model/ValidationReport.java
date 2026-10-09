package toverify.model; // Places this class in the model package

import java.util.ArrayList; // Used to create the error list
import java.util.List; // Used to store validation errors

public class ValidationReport { // Stores the results of TO validation

    private final List<ValidationError> errors; // Stores all validation errors

    public ValidationReport() { // Creates a new validation report
        this.errors = new ArrayList<>(); // Creates an empty error list
    }

    public void addError(String code, String message) { // Adds a validation error
        errors.add(new ValidationError(code, message)); // Creates and stores the error
    }

    public List<ValidationError> getErrors() { // Gets all validation errors
        return errors; // Returns the error list
    }

    public boolean isValid() { // Checks if the TO passed validation
        return errors.isEmpty(); // Returns true when there are no errors
    }

    public int getErrorCount() { // Gets the number of validation errors
        return errors.size(); // Returns the number of errors
    }
}
