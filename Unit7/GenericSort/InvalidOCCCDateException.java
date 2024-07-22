/**
 * AUTHOR: Jon Pack
 * OCCC - ADVANCED JAVA
 * DATE: 04 13, 2024
 * PROJECT NAME: InvalidOCCCDateException.java
 * DESCRIPTION: exceptions
 */
public class InvalidOCCCDateException extends Exception {
    private static final String DEFAULT_MESSAGE = "Invalid OCCCDate";
    private String message;

    public InvalidOCCCDateException() {
        super();
        message = DEFAULT_MESSAGE;
    }

    public InvalidOCCCDateException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String toString() {
        return "InvalidOCCCDateException: " + message;
    }
}

// code for the OCCCDate program with an Exception added to it
// for the checked Exception we import Exception/
// of rhte unChecked Exception we import IllegalArguementException
// the Contructor is the one that create the EXception
// the Contructor it is also the one that throws out the Exception;
