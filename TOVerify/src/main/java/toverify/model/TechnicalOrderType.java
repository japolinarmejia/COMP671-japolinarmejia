package toverify.model; // Places this enum in the model package

public enum TechnicalOrderType { // Defines the supported TO types

    OPERATIONS, // Represents an Operations TO ending in -1
    MAINTENANCE, // Represents a Maintenance TO ending in -2
    INSTALLATION, // Represents an Installation TO ending in -7
    UNKNOWN // Represents an unrecognized TO type
}