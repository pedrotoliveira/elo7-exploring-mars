package br.com.elo7.mars.explorer.engine.application;

import br.com.elo7.mars.explorer.engine.domain.Factory;
import br.com.elo7.mars.explorer.engine.domain.explorer.Direction;
import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import br.com.elo7.mars.explorer.engine.domain.explorer.InstructionAction;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import br.com.elo7.mars.explorer.engine.domain.surface.SurfaceRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 *
 * Unit tests of the PlateauScanEngine.
 *
 * Test the Expected Work Flow for the Engine.
 *
 * @author pedrotoliveira
 */
@RunWith(value = MockitoJUnitRunner.class)
public class PlateauScanEngineTest {

	private PlateauScanEngine scanEngine;
	@Mock
	private Factory<Surface> surfaceFactory;
	@Mock
	private Factory<Explorer> explorerFactory;
	@Mock
	private Factory<Collection<InstructionAction>> instructionCollectionFactory;
	@Mock
	private SurfaceRepository surfaceRepository;

	@Before
	public void setUp() {
		this.scanEngine = new PlateauScanEngine(surfaceFactory, explorerFactory, instructionCollectionFactory, surfaceRepository);
	}

	@Test
	@SuppressWarnings("serial")
	public void testCreateSurfaceAndScan() {
		Collection<String> inputs = createTestInputs();
		Iterator<String> inputsIterator = inputs.iterator();
		Surface surface = createTestSurface(inputsIterator);
		List<Explorer> deployedExplorers = createTestExplorers(inputsIterator, surface);
				
		when(surface.getDeployedExplorers()).thenReturn(deployedExplorers);		
		when(surfaceRepository.save(surface)).thenReturn(surface);
		
		assertThat(scanEngine.createSurfaceAndScan(inputs), equalTo(surface));

		verify(surfaceFactory, times(1)).create(inputs.iterator().next());
		verify(explorerFactory, times(2)).create(anyString());
		verify(instructionCollectionFactory, times(2)).create(anyString());
		verify(surface, times(2)).deployExplorer(any(Explorer.class));
		verify(surface, times(1)).getDeployedExplorers();
		verify(surfaceRepository, atLeastOnce()).save(surface);
		deployedExplorers.forEach((Explorer explorer) -> {
			verify(explorer, atLeastOnce()).registerInstructions(anyCollectionOf(InstructionAction.class));
			verify(explorer, atLeastOnce()).excuteInstructions(surface);
		});
	}

	private Collection<String> createTestInputs() {
		return new ArrayList<String>() {{
			add("5 5");
			add("1 2 N");
			add("LMLMLMLMM");
			add("3 3 E");
			add("MMRMMRMRRM");
		}};
	}

	private Collection<String> createExpectedResult() {
		Collection<String> expectedResults = new ArrayList<String>() {{
			add("1 3 N");
			add("5 1 E");
		}};
		return expectedResults;
	}

	private Surface createTestSurface(Iterator<String> inputsIterator) {
		String surfaceInput = inputsIterator.next();
		Surface surface = mock(Surface.class);
		when(surfaceFactory.create(surfaceInput)).thenReturn(surface);
		return surface;
	}
	
	@SuppressWarnings("unchecked")
	private List<Explorer> createTestExplorers(Iterator<String> inputsIterator, Surface surface) {
		List<Explorer> deployedExplorers = new ArrayList<>();
		while (inputsIterator.hasNext()) {
			String explorerInput = inputsIterator.next();
			String instructionInput = inputsIterator.next();

			Explorer explorer = mock(Explorer.class);
			Collection<InstructionAction> instructions = mock(Collection.class);

			when(explorerFactory.create(explorerInput)).thenReturn(explorer);
			when(instructionCollectionFactory.create(instructionInput)).thenReturn(instructions);
			when(surface.deployExplorer(explorer)).thenReturn(explorer);

			deployedExplorers.add(explorer);
		}
		return deployedExplorers;
	}
			
	@Test(expected = IllegalArgumentException.class)
	public void testNullInput() {
		assertExceptionMessage(null, "Missing Inputs");
	}

	@Test(expected = IllegalArgumentException.class)
	@SuppressWarnings("serial")
	public void testMissingExplorerInput() {
		Collection<String> badInputs = new ArrayList<String>() {{
			add("5 5");
		}};
		assertExceptionMessage(badInputs, "Missing Explorer Inputs");
	}

	@Test(expected = IllegalArgumentException.class)
	@SuppressWarnings("serial")
	public void testMissingInstructionsInput() {
		Collection<String> badInputs = new ArrayList<String>() {{
			add("5 5");
			add("1 2 Z");
		}};
		assertExceptionMessage(badInputs, "Missing Instructions Input");
	}

	private void assertExceptionMessage(Collection<String> badInputs, String expectedMessage) {
		try {
			scanEngine.createSurfaceAndScan(badInputs);
		} catch (Exception ex) {
			assertThat(ex.getMessage(), equalTo(expectedMessage));
			throw ex;
		}
	}
	
	@Test	
	public void testCreateSurface() {
		String surfaceInput  = "10 10";
		Surface surface = mock(Surface.class);
		when(surfaceFactory.create(surfaceInput)).thenReturn(surface);
		when(surfaceRepository.save(surface)).thenReturn(surface);
		
		assertThat(scanEngine.createSurface(surfaceInput), equalTo(surface));
		
		verify(surfaceFactory, times(1)).create(surfaceInput);
		verify(surfaceRepository, times(1)).save(surface);
	}
	
	@Test	
	public void testDeployExplorers() {
		String surfaceId = UUID.randomUUID().toString();
		Collection<String> explorerInputs = new ArrayList<String>() {{			
			add("1 2 N");
			add("LMLMLMLMM");
			add("3 3 E");
			add("MMRMMRMRRM");
		}};
		
		Surface surface = mock(Surface.class);
		when(surfaceRepository.findOne(surfaceId)).thenReturn(surface);		
		List<Explorer> deployedExplorers = createTestExplorers(explorerInputs.iterator(), surface);
		when(surface.getDeployedExplorers()).thenReturn(deployedExplorers);
		when(surfaceRepository.save(surface)).thenReturn(surface);
		
		assertThat(scanEngine.deployExplorers(surfaceId, explorerInputs), equalTo(surface));
		
		verify(surfaceRepository, atLeastOnce()).findOne(surfaceId);
		verify(explorerFactory, times(2)).create(anyString());
		verify(instructionCollectionFactory, times(2)).create(anyString());
		verify(surface, times(2)).deployExplorer(any(Explorer.class));		
		verify(surfaceRepository, atLeastOnce()).save(surface);
		deployedExplorers.forEach((Explorer explorer) -> {
			verify(explorer, atLeastOnce()).registerInstructions(anyCollectionOf(InstructionAction.class));
		});
	}
	
	@Test
	public void testScan() {				
		String surfaceId = UUID.randomUUID().toString();
		Collection<String> expectedResults = createExpectedResult();
		
		Surface surface = mock(Surface.class);
		when(surfaceRepository.findOne(surfaceId)).thenReturn(surface);
		
		Collection<String> explorerInputs = new ArrayList<String>() {{			
			add("1 2 N");
			add("LMLMLMLMM");
			add("3 3 E");
			add("MMRMMRMRRM");
		}};
		
		List<Explorer> deployedExplorers = createTestExplorers(explorerInputs.iterator(), surface);
		when(surface.getDeployedExplorers()).thenReturn(deployedExplorers);
		when(surfaceRepository.save(surface)).thenReturn(surface);
		
		assertThat(scanEngine.scan(surfaceId), equalTo(surface));
		
		verify(surface, times(1)).getDeployedExplorers();
		verify(surfaceRepository, atLeastOnce()).save(surface);
		deployedExplorers.forEach((Explorer explorer) -> {			
			verify(explorer, atLeastOnce()).excuteInstructions(surface);
		});
	}
	
	@Test
	public void testDelete() {
		String surfaceId = UUID.randomUUID().toString();
		Surface surface = mock(Surface.class);		
		when(surfaceRepository.findOne(surfaceId)).thenReturn(surface);
		scanEngine.deleteSurface(surfaceId);
		verify(surfaceRepository, times(1)).findOne(surfaceId);
		verify(surfaceRepository, times(1)).delete(surface);
	}
}
