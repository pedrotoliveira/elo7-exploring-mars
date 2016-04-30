package br.com.elo7.mars.explorer.engine.domain.surface;

import br.com.elo7.mars.explorer.engine.domain.Factory;
import br.com.elo7.mars.explorer.engine.domain.validator.CoordinateValidator;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Encapsulate
 *
 * @author pedrotoliveira
 */
public class SurfaceFactory implements Factory<Surface> {

    @Override
    public Surface create(String input) {
        Objects.requireNonNull(input, "Surface Input Is Null");
        if (!Pattern.matches("^\\d{0,32}\\s\\d{0,32}$", input)) {
            throw new IllegalArgumentException("Invalid Input Format");
        }
        Scanner scanner = new Scanner(input);
        int xAxis = scanner.nextInt();
        int yAxis = scanner.nextInt();
        CoordinateValidator.validate(xAxis, yAxis);
        return new Plateau(UUID.randomUUID(), xAxis, yAxis);
    }
}
