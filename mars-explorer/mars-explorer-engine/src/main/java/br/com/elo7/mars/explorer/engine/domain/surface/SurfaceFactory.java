package br.com.elo7.mars.explorer.engine.domain.surface;

import br.com.elo7.mars.explorer.engine.domain.Factory;
import br.com.elo7.mars.explorer.engine.domain.validator.CoordinateValidator;
import br.com.elo7.mars.explorer.engine.domain.validator.InputRegexValidator;
import java.util.Scanner;
import java.util.UUID;
import org.apache.commons.lang.Validate;

/**
 * Encapsulate
 *
 * @author pedrotoliveira
 */
public class SurfaceFactory implements Factory<Surface> {

    @Override
    public Surface create(String input) {
        Validate.notNull(input, "Surface Input Is Null");
        InputRegexValidator.validate("^\\d{0,10}\\s\\d{0,10}$", input);
        Scanner scanner = new Scanner(input);
        int xAxis = scanner.nextInt();
        int yAxis = scanner.nextInt();
        CoordinateValidator.validate(xAxis, yAxis);
        return new Plateau(UUID.randomUUID(), xAxis, yAxis);
    }
}
