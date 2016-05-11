package br.com.elo7.mars.explorer.engine.domain.converter;

import br.com.elo7.mars.explorer.engine.domain.explorer.Instruction;
import br.com.elo7.mars.explorer.engine.domain.explorer.InstructionAction;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * String To Instruction Action Converter
 * 
 * @author pedrotoliveira
 */
@Component
public class InstructionActionReadConverter implements Converter<String, InstructionAction> {
	
	@Override
	public InstructionAction convert(String source) {
		return Instruction.valueOf(source);
	}
}
