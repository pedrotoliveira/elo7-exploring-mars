package br.com.elo7.mars.explorer.engine.domain;

/**
 * Abstract Factory.
 * 
 * @author pedrotoliveira
 */
public interface Factory<Entity> {
	
	/**
	 * Interprets a String input and Create a Entity.
	 * 
	 * @param input
	 * @return 
	 */
	Entity create(String input);
}
