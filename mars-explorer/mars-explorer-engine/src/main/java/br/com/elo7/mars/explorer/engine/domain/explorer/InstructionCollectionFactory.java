package br.com.elo7.mars.explorer.engine.domain.explorer;

import br.com.elo7.mars.explorer.engine.domain.Factory;
import br.com.elo7.mars.explorer.engine.domain.validator.InputRegexValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.lang.Validate;

/**
 *
 * @author pedrotoliveira
 */
public class InstructionCollectionFactory implements Factory<Collection<InstructionAction>> {

	@Override
	public Collection<InstructionAction> create(String input) {
		Validate.notNull(input, "Instructions Input is Null");
		InputRegexValidator.validate("^[MLR]+", input);
		Collection<InstructionAction> instructions = new ArrayList<>();
		for (char representation : input.toCharArray()) {
			instructions.add(Instruction.translate(String.valueOf(representation)));
		}
		return instructions;
	}

}
