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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

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
		Collection<String> expectedResults = createExpectedResult();
		Iterator<String> inputsIterator = inputs.iterator();

		Surface surface = createTestSurface(inputsIterator);
		List<Explorer> deployedExplorers = createTestExplorers(inputsIterator, surface);
		List<ExplorerPosition> expectedPositions = createExpectedPositions(expectedResults);
		doExpectedExplorerInvocations(deployedExplorers, expectedPositions);
		when(surface.getDeployedExplorers()).thenReturn(deployedExplorers);
		when(surfaceRepository.save(surface)).thenReturn(surface);
		
		assertThat(scanEngine.createSurfaceAndScan(inputs), equalTo(expectedResults));

		verify(surfaceFactory, times(1)).create(inputs.iterator().next());
		verify(explorerFactory, times(2)).create(anyString());
		verify(instructionCollectionFactory, times(2)).create(anyString());
		verify(surface, times(2)).deployExplorer(any(Explorer.class));
		verify(surface, times(1)).getDeployedExplorers();
		verify(surfaceRepository, atLeastOnce()).save(surface);
		deployedExplorers.forEach((Explorer explorer) -> {
			verify(explorer, atLeastOnce()).registerInstructions(anyCollectionOf(InstructionAction.class));
			verify(explorer, atLeastOnce()).excuteInstructions(surface);
			verify(explorer, atLeastOnce()).getCurrentPosition();
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
	
	private List<ExplorerPosition> createExpectedPositions(final Collection<String> expectedResults) {
		List<ExplorerPosition> expectedPositions = new ArrayList<>();
		expectedResults.forEach((String result) -> {
			Scanner scanner = new Scanner(result);
			ExplorerPosition position = new ExplorerPosition(
					scanner.nextInt(),
					scanner.nextInt(),
					Direction.translate(scanner.next()));

			expectedPositions.add(position);
		});
		return expectedPositions;
	}	
		
	private void doExpectedExplorerInvocations(List<Explorer> deployedExplorers, List<ExplorerPosition> expectedPositions) {
		int index = 0;
		for (Explorer explorer : deployedExplorers) {
			when(explorer.getCurrentPosition()).thenReturn(expectedPositions.get(index++));
		}
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
		fail("To Implement");
	}
	
	@Test
	public void testDeployExplorers() {
		fail("To Implement");
	}
	
	@Test
	public void testScan() {
		fail("To Implement");
	}
}
