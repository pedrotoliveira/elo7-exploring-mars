package br.com.elo7.mars.explorer.engine.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * MarsExplorerEngineApplication Tests
 *
 * @author pedrotoliveira
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(MarsExplorerEngineApplication.class)
public class MarsExplorerEngineApplicationTest {
	
	@Autowired
	MarsExplorerEngineApplication application;
	
	@Test
	public void testRun() throws Exception {		
		application.run("5 5", "1 2 N", "LMLMLMLMM", "3 3 E", "MMRMMRMRRM");
	}
}
