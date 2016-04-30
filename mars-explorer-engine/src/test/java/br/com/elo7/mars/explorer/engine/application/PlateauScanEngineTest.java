package br.com.elo7.mars.explorer.engine.application;

import br.com.elo7.mars.explorer.engine.domain.Factory;
import br.com.elo7.mars.explorer.engine.domain.explorer.Direction;
import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import br.com.elo7.mars.explorer.engine.domain.explorer.Instruction;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
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
	private Factory<Collection<Instruction>> instructionCollectionFactory;

	@Before
	public void setUp() {
		this.scanEngine = new PlateauScanEngine(surfaceFactory, explorerFactory, instructionCollectionFactory);
	}

	@Test
    @SuppressWarnings("unchecked")
	public void testCreateSurfaceAndScan() {
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

		List<Explorer> deployedExplorers = new ArrayList<>();
		List<Collection<Instruction>> instructionsList = new ArrayList<>();

		while(it.hasNext()) {
			String createExplorerInput = it.next();
			String createInstructionsInput = it.next();

			Explorer explorer = mock(Explorer.class);
			Collection<Instruction> instructions = mock(Collection.class);

			when(instructionCollectionFactory.create(createInstructionsInput)).thenReturn(instructions);
			when(explorerFactory.create(createExplorerInput)).thenReturn(explorer);
			when(surface.deployExplorer(explorer)).thenReturn(explorer);

			deployedExplorers.add(explorer);
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

			when(deployedExplorers.get(index++).getCurrentPosition()).thenReturn(position);
			expectedPositions.add(position);
		}
        
		assertThat(scanEngine.createSurfaceAndScan(inputs), equalTo(expectedResults));

		verify(surfaceFactory, times(1)).create(createSurfaceInput);
		verify(explorerFactory, times(2)).create(anyString());
		verify(instructionCollectionFactory, times(2)).create(anyString());
		verify(surface, times(2)).deployExplorer(any(Explorer.class));
        deployedExplorers.forEach((Explorer item) -> {
            verify(item).registerInstructions(anyCollectionOf(Instruction.class));
            verify(item).excuteInstructions(surface);
            verify(item).getCurrentPosition();
        });
	}

	@Test(expected = NullPointerException.class)
	public void testNullInput() {
		scanEngine.createSurfaceAndScan(null);
	}


	@Test(expected = IllegalArgumentException.class)
	public void testBadInputSequence() {
        Collection<String> badInputs = new ArrayList<String>() {{
			add("5 5");
			add("1 2 Z");
		}};
		scanEngine.createSurfaceAndScan(badInputs);
	}
}
