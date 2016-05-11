package br.com.elo7.mars.explorer.engine.domain.converter;

import br.com.elo7.mars.explorer.engine.domain.explorer.Instruction;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Instruction Action Read Converter Unit Tests
 * 
 * @author pedrotoliveira
 */
public class InstructionActionReadConverterTest {
	
	private final InstructionActionReadConverter converter = new InstructionActionReadConverter();
	
	@Test
	public void testConvert() {
		String TURN_LEFT = "TURN_LEFT";
		assertThat(converter.convert(TURN_LEFT), equalTo(Instruction.TURN_LEFT));
	}
}
