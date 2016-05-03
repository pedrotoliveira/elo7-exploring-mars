package br.com.elo7.mars.explorer.engine.domain.explorer;

import br.com.elo7.mars.explorer.engine.domain.Factory;
import br.com.elo7.mars.explorer.engine.domain.validator.CoordinateValidator;
import br.com.elo7.mars.explorer.engine.domain.validator.InputRegexValidator;
import java.util.Scanner;
import java.util.UUID;
import org.apache.commons.lang.Validate;

/**
 * Validate a String Input and Create an Explorer.
 * 
 * @author pedrotoliveira
 */
public class ExplorerFactory implements Factory<Explorer> {

	@Override
	public Explorer create(String input) {
		Validate.notNull(input, "Surface Input Is Null");
        InputRegexValidator.validate("^\\d{0,10}\\s\\d{0,10}\\s[NEWS]$", input);
        Scanner scanner = new Scanner(input);
        int xAxis = scanner.nextInt();
        int yAxis = scanner.nextInt();
        CoordinateValidator.validate(xAxis, yAxis);
        Direction direction = Direction.translate(scanner.next());
        return new MarsExplorer(UUID.randomUUID(), xAxis, yAxis, direction);
	}
}
