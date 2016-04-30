package br.com.elo7.mars.explorer.engine.domain.validator;

import java.util.regex.Pattern;

/**
 * Validate a String input matching a given regular expression.
 *
 * @author pedrotoliveira
 */
public class InputRegexValidator {

    public static final String ERROR_MESSAGE = "Invalid Input Format! value=";

    private InputRegexValidator() {
    }

    public static void validate(String pattern, String input) {
        if (!Pattern.matches(pattern, input)) {
            throw new IllegalArgumentException(ERROR_MESSAGE + input);
        }
    }
}
