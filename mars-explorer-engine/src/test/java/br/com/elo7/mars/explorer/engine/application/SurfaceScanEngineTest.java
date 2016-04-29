package br.com.elo7.mars.explorer.engine.application;

import br.com.elo7.mars.explorer.engine.domain.Factory;
import br.com.elo7.mars.explorer.engine.domain.explorer.Direction;
import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import br.com.elo7.mars.explorer.engine.domain.explorer.Instruction;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 *
 * Unit tests of the SurfaceScanEngine. 
 * 
 * Test the Expected Work Flow for the Engine.
 *
 * @author pedrotoliveira
 */
@RunWith(value = MockitoJUnitRunner.class)
public class SurfaceScanEngineTest {

	private SurfaceScanEngine scanEngine;
	@Mock
	private Factory<Surface> surfaceFactory;
	@Mock
	private Factory<Explorer> explorerFactory;
	@Mock
	private Factory<Collection<Instruction>> instructionCollectionFactory;
	
	@Before
	public void setUp() {
		this.scanEngine = new SurfaceScanEngine(surfaceFactory, explorerFactory, instructionCollectionFactory);
	}
	
	@Test
	public void testProcess() {
		Collection<String> inputs = new ArrayList<String>() {{
			add("5 5");
			add("1 2 N");
			add("LMLMLMLMM");
			add("3 3 E");
			add("MMRMMRMRRM");
		}};
		
		Collection<String> expectedResults = new ArrayList<String>() {{
			add("1 3 N");
			add("5 1 E");
		}};
		
		Iterator<String> it = inputs.iterator();
		String createSurfaceInput = it.next();
		
		Surface surface = mock(Surface.class);
		when(surfaceFactory.create(createSurfaceInput)).thenReturn(surface);
		
		List<Explorer> explorers = new ArrayList<>();
		List<Collection<Instruction>> instructionsList = new ArrayList<>();
		
		while(it.hasNext()) {
			String createExplorerInput = it.next();
			String createInstructionsInput = it.next();						
			
			Explorer explorer = mock(Explorer.class);			
			Collection<Instruction> instructions = mock(Collection.class);
			
			when(instructionCollectionFactory.create(createInstructionsInput)).thenReturn(instructions);
			when(explorerFactory.create(createExplorerInput)).thenReturn(explorer);
			when(surface.deployExplorer(explorer)).thenReturn(explorer);
			
			explorers.add(explorer);
			instructionsList.add(instructions);
		}
		
		Collection<ExplorerPosition> expectedPositions = new ArrayList<>();		
		int index = 0;		
		for (String expectedResult : expectedResults) {
			Scanner scanner = new Scanner(expectedResult);
			ExplorerPosition position = new ExplorerPosition(
					scanner.nextInt(),
					scanner.nextInt(),
					Direction.translate(scanner.next()));
			
			when(explorers.get(index++).getCurrentPosition()).thenReturn(position);
			expectedPositions.add(position);
		}
		when(surface.getExplorerPositions()).thenReturn(expectedPositions);
		
		assertThat(scanEngine.process(inputs), equalTo(expectedResults));
				
		verify(surfaceFactory, times(1)).create(createSurfaceInput);		
		verify(explorerFactory, times(2)).create(anyString());
		verify(instructionCollectionFactory, times(2)).create(anyString());
		verify(surface, times(2)).deployExplorer(any(Explorer.class));
	}
}
