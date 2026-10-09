# TOVerify

**Technical Order Validation Tool — Version 1.0**

**Course:** COMP671 – Software Verification and Testing  
**Testing Framework:** JUnit 5  
**Coverage Tool:** JaCoCo  
**Programming Language:** Java 25  
**Build Tool:** Apache Maven

## 1. Project Overview

TOVerify is a Java-based application developed to validate the structure and required content of Technical Orders (TOs). Technical Orders are documents used to provide technical instructions for operating, installing, and maintaining systems and equipment.

The purpose of this project is to demonstrate software verification and testing techniques using JUnit 5. TOVerify reads a plain-text Technical Order, identifies its type, evaluates defined validation requirements, and produces a report identifying any errors.

Version 1.0 focuses on Installation Technical Orders identified by the `-7` suffix.

## 2. Project Scope

TOVerify supports the following functionality:

- Reads Technical Orders in `.txt` format.
- Extracts document metadata and numbered sections.
- Identifies Technical Order types based on their numbering.
- Validates required metadata.
- Checks section hierarchy.
- Validates required Installation TO chapters and subsections.
- Generates validation results with error codes and descriptions.

The application recognizes three Technical Order types:

| TO Suffix | Type | Version 1.0 Support |
|---|---|---|
| `-7` | Installation | Full validation |
| `-1` | Operations | Identification only |
| `-2` | Maintenance | Identification only |

Unrecognized TO types are rejected.

The Installation TO content requirements used in this project are simplified, project-defined rules. They are not intended to represent a complete official Air Force Technical Order validation standard.

## 3. Validation Requirements

### Metadata Validation

| Requirement | Description |
|---|---|
| META-001 | Technical Order number must be present. |
| META-002 | Technical Order title must be present. |
| META-003 | Classification must be present. |
| META-004 | Version must be present. |

### Document Structure Validation

| Requirement | Description |
|---|---|
| STR-001 | Section hierarchy cannot exceed four levels. |

Section numbering sequence and parent-child relationships are identified as potential future enhancements.

### Installation Technical Order Validation

Installation Technical Orders must contain Chapters 1 through 6 and the following required subsections:

| Chapter | Required Sections |
|---|---|
| 1 | Purpose, System Description, Network Diagram |
| 2 | Hardware, Software, Firmware, Locations |
| 3 | Installation Requirements, Pre-Installation Check |
| 4 | Install Router |
| 5 | Router Configuration |
| 6 | System Hardening |

The application reports `INST-001` for missing chapters and `INST-002` for missing required subsections.

## 4. Application Design

TOVerify separates document parsing from validation.

The `TechnicalOrderParser` reads the text file and creates a Technical Order object containing metadata and document sections.

The `TOTypeDetector` determines whether the document is an Installation, Operations, Maintenance, or unknown TO.

The `TechnicalOrderValidator` coordinates the individual validators:

- `MetadataValidator` checks required document information.
- `StructureValidator` checks section hierarchy.
- `InstallationValidator` checks Installation TO requirements.

Validation errors are collected in a `ValidationReport` and displayed through the command-line interface.

## 5. System Requirements

The project was developed and tested using:

- Java Development Kit 25
- Apache Maven 3.10.0
- JUnit Jupiter 5.11.4
- JaCoCo 0.8.14
- Visual Studio Code

## 6. Building and Running the Application

Open PowerShell in the `TOVerify` project directory.

Compile the application:

```powershell
mvn compile
```

Run the complete JUnit test suite:

```powershell
mvn test
```

Run the tests and generate the JaCoCo coverage report:

```powershell
mvn verify
```

Run TOVerify against the valid sample Technical Order:

```powershell
java -cp target/classes toverify.Main samples/valid_installation_to.txt
```

Run TOVerify against an invalid sample:

```powershell
java -cp target/classes toverify.Main samples/invalid_metadata_to.txt
```

The program displays the Technical Order number, title, detected type, number of sections, and validation results.

## 7. Sample Technical Orders

The project includes four synthetic Technical Orders for testing.

| Sample File | Purpose | Expected Result |
|---|---|---|
| `valid_installation_to.txt` | Complete Installation TO | PASS |
| `invalid_metadata_to.txt` | Missing classification | META-003 |
| `invalid_structure_to.txt` | Exceeds four hierarchy levels | STR-001 |
| `invalid_content_to.txt` | Missing Network Diagram | INST-002 |

These files contain fictional technical information and are intended only for educational testing.

## 8. Automated Testing

JUnit 5 is used to verify application behavior through automated unit and integration tests.

The test suite covers:

- Technical Order parsing.
- TO type detection.
- Required metadata validation.
- Document hierarchy validation.
- Installation TO content validation.
- Overall validation coordination.
- Command-line application behavior.
- Integration testing using sample documents.

The tests include valid inputs, invalid inputs, boundary conditions, and unsupported TO types.

### Verified Test Results

| Metric | Result |
|---|---|
| Total JUnit tests | 27 |
| Passed | 27 |
| Failed | 0 |
| Errors | 0 |
| Skipped | 0 |

All 27 automated tests passed during the final Maven verification.

## 9. Code Coverage

JaCoCo was integrated with Maven to measure how much of the application code was executed during testing.

The final verified coverage results were:

| Coverage Metric | Result |
|---|---|
| Instruction coverage | 99% |
| Branch coverage | 92% |
| Instructions covered | 712 of 718 |
| Branches covered | 72 of 78 |
| Classes analyzed | 12 |

The coverage report can be opened at:

`target/site/jacoco/index.html`

Although coverage is high, it does not guarantee the absence of defects. Additional tests and validation requirements may be added in future versions.

## 10. Defect Identification and Correction

During testing, a duplicate `TechnicalOrderValidator` class was discovered in the test source directory.

This duplicate caused a mismatch between the classes executed by JUnit and those analyzed by JaCoCo. After removing the duplicate, four JUnit tests exposed missing handling for Operations, Maintenance, and unknown Technical Order types in the production validator.

The production implementation was corrected, and the complete test suite was executed again.

Final verification confirmed that all 27 tests passed and the JaCoCo class-mismatch warning was resolved.

This demonstrated the value of automated testing, regression testing, and code coverage analysis in identifying and correcting software defects.

## 11. Current Limitations

Version 1.0 has the following limitations:

- Only plain-text Technical Orders are supported.
- Detailed validation is limited to Installation TOs.
- Operations and Maintenance TOs are recognized but not fully validated.
- Section numbering sequence is not currently validated.
- Parent-child section relationships are not currently validated.
- The validation rules represent a simplified educational model.
- No graphical user interface is provided.

## 12. Future Improvements

Potential enhancements include support for Operations and Maintenance TO validation, additional document structure checks, PDF and Word document processing, expanded test cases, and automated testing through a continuous integration workflow.

## 13. Conclusion

TOVerify demonstrates the application of automated software verification and testing to a Technical Order validation program.

JUnit 5 was used to verify expected behavior, identify defects, and support regression testing. JaCoCo provided measurable evidence of test coverage.

The completed project successfully executed 27 automated tests with 99% instruction coverage and 92% branch coverage, demonstrating how automated testing tools can support software quality and reliability.