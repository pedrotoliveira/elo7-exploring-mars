package br.com.elo7.mars.explorer.engine.domain.validator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 *
 * @author pedrotoliveira
 */
public class InputRegexValidatorTest {

    @Test(expected = IllegalArgumentException.class)
    public void testValidate() {
        String input = "q9 923 q";
        String pattern = "^\\d{0,32}\\s\\d{0,32}\\s[NEWS]$";
        try {
            InputRegexValidator.validate(pattern, input);
        } catch (Exception ex) {
            assertThat(ex.getMessage(), equalTo(InputRegexValidator.ERROR_MESSAGE + input + "\""));
            throw ex;
        }
    }
}
