package br.com.elo7.mars.explorer.engine.domain.explorer;

import br.com.elo7.mars.explorer.engine.domain.Factory;
import java.util.Collection;

/**
 *
 * @author pedrotoliveira
 */
public class InstructionCollectionFactory implements Factory<Collection<InstructionAction>> {

	@Override
	public Collection<InstructionAction> create(String input) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
