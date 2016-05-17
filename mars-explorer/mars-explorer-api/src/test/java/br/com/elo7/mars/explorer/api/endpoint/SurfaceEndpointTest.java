package br.com.elo7.mars.explorer.api.endpoint;

import br.com.elo7.mars.explorer.api.bootstrap.MarsExplorerApiApplication;
import br.com.elo7.mars.explorer.api.resource.Dimension;
import br.com.elo7.mars.explorer.api.resource.ExplorerResource;
import br.com.elo7.mars.explorer.api.resource.SurfaceResource;
import br.com.elo7.mars.explorer.api.resource.adapter.SurfaceAdapter;
import br.com.elo7.mars.explorer.api.service.SurfaceService;
import br.com.elo7.mars.explorer.engine.application.SurfaceScanEngine;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import br.com.elo7.mars.explorer.engine.test.FixtureTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static java.net.URI.create;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Surface Endpoint Tests
 *
 * @author pedrotoliveira
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MarsExplorerApiApplication.class)
@WebAppConfiguration
public class SurfaceEndpointTest extends FixtureTest {

    @Autowired
    private SurfaceService surfaceService;
    @Autowired
    private SurfaceScanEngine scanEngine;
	@Autowired
	private SurfaceAdapter surfaceAdapter;	
	
	private UriInfo uriInfo;	
	private Surface surface;
	private SurfaceEndpoint endpoint;
	
    @Before
    public void setUp() throws Exception {
		this.endpoint = new SurfaceEndpoint();
		this.surfaceService = Mockito.spy(surfaceService);		
		this.endpoint.setSurfaceService(surfaceService);
		this.uriInfo = Mockito.mock(UriInfo.class);
		this.endpoint.setUriInfo(uriInfo);
		
		this.surface = scanEngine.createSurface(validSurfaceInput());
		this.surface = scanEngine.deployExplorers(surface.getId(), validExplorersInputs());
		this.surface = scanEngine.scan(surface.getId());
    }

    @After
    public void tearDown() throws Exception {
		scanEngine.deleteSurface(surface.getId());
    }

    @Test
    public void testGet() {
		boolean expand = true;
		Resource<SurfaceResource> expected = surfaceAdapter.adapt(surface, expand);		
		Response response = endpoint.get(surface.getId(), expand);
		assertThat(response.getStatus(), equalTo(200));
		assertThat(response.getEntity(), equalTo(expected));
		verify(surfaceService).findById(surface.getId(), expand);
    }

    @Test
    public void testPost() {
		SurfaceResource request = postRequest();		
		boolean expand = true;
		boolean details = true;
		
		when(uriInfo.getRequestUri()).thenReturn(create("/surface"));
		
		Response response = endpoint.post(request, expand, details);
		assertThat(response.getStatus(), equalTo(201));
		
		SurfaceResource surfaceResponse = ((Resource<SurfaceResource>) response.getEntity()).getContent();
		Iterator<String> positions = surfaceResponse.getExplorersPositions().iterator();
		assertThat(positions.next(), equalTo("1 3 N"));
		assertThat(positions.next(), equalTo("5 1 E"));
		
		verify(surfaceService).createSurfaceAndScan(request, expand);
		verify(uriInfo).getRequestUri();
    }

	public SurfaceResource postRequest() {
		List<ExplorerResource> explorerResources = new ArrayList<>();
		explorerResources.add(new ExplorerResource(1, 2, "N").instructions("LMLMLMLMM"));
		explorerResources.add(new ExplorerResource(3, 3, "E").instructions("MMRMMRMRRM"));
		Resources<ExplorerResource> deployedExplorers = new Resources<>(explorerResources);
		SurfaceResource request = new SurfaceResource()
				.dimension(new Dimension(5, 5))
				.deployedExplorers(deployedExplorers);
		
		return request;
	}

    @Test
    public void testPatch() {
		SurfaceResource request = postRequest();		
		boolean expand = true;
		boolean details = true;
		when(uriInfo.getRequestUri()).thenReturn(create("/surface"));		
		Response response = endpoint.post(request, expand, details);		
		SurfaceResource createdSurface = ((Resource<SurfaceResource>) response.getEntity()).getContent();
		
		SurfaceResource patchRequest = patchRequest(createdSurface);
		
		response = endpoint.patch(patchRequest, expand, details);
		assertThat(response.getStatus(), equalTo(200));
		
		SurfaceResource surfaceResponse = ((Resource<SurfaceResource>) response.getEntity()).getContent();
		Iterator<String> positions = surfaceResponse.getExplorersPositions().iterator();
		assertThat(positions.next(), equalTo("1 3 N"));
		assertThat(positions.next(), equalTo("5 1 E"));
		assertThat(positions.next(), equalTo("2 3 N"));
		assertThat(positions.next(), equalTo("1 5 W"));
		
		verify(surfaceService).createSurfaceAndScan(request, expand);
		verify(uriInfo).getRequestUri();
		verify(surfaceService).updateSurfaceAndScan(patchRequest, expand);
    }
	
	public SurfaceResource patchRequest(SurfaceResource createdSurface) {
		List<ExplorerResource> explorerResources = new ArrayList<>();
		explorerResources.add(new ExplorerResource(2, 2, "N").instructions("LMLMLMLMM"));
		explorerResources.add(new ExplorerResource(3, 4, "W").instructions("MMRMMRMRRM"));		
		Resources<ExplorerResource> deployedExplorers = new Resources<>(explorerResources);
		SurfaceResource request = new SurfaceResource()
				.id(createdSurface.getId())
				.dimension(new Dimension(10, 10))
				.deployedExplorers(deployedExplorers);		
		return request;
	}

    @Test
    public void testDelete() {
		Surface newSurface = scanEngine.createSurface(validSurfaceInput());
		String id = newSurface.getId();
		assertThat(endpoint.delete(id).getStatus(), equalTo(200));
		verify(surfaceService).delete(id);
    }

    @Test
    public void testCreateExplorerResource() {
		boolean expand = true;
		String id = surface.getId();
		ExplorerResource explorerResource = new ExplorerResource(2, 2, "N").instructions("LMLMLMLMM");
		Response response = endpoint.post(id, explorerResource, expand);
		assertThat(response.getStatus(), equalTo(200));
		verify(surfaceService).deployAndScan(id, explorerResource, expand);
    }
	
	private String validSurfaceInput() {
		return String.format("%d %d", 100, 100);
	}

	private Collection<String> validExplorersInputs() {
		Collection<String> explorersInput = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			explorersInput.add(String.format("%d %d %s", (i * 3), (i * 7), regexValue("[NEWS]")));
			explorersInput.add(regexValue("[MLR]{25}"));
		}
		return explorersInput;
	}
}
