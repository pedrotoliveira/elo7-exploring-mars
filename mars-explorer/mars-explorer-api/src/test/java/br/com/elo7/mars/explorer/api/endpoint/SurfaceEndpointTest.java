package br.com.elo7.mars.explorer.api.endpoint;

import br.com.elo7.mars.explorer.api.bootstrap.MarsExplorerApiApplication;
import br.com.elo7.mars.explorer.api.service.SurfaceService;
import br.com.elo7.mars.explorer.engine.application.SurfaceScanEngine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Surface Endpoint Tests
 *
 * @author pedrotoliveira
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MarsExplorerApiApplication.class)
@WebAppConfiguration
public class SurfaceEndpointTest {

    @Autowired
    private SurfaceService surfaceService;
    @Autowired
    private SurfaceScanEngine scanEngine;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGet() {
    }

    @Test
    public void testPost() {
    }

    @Test
    public void testPatch() {
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testCreateExplorerResource() {
    }

    @Test
    public void testGetSurfaceService() {
    }
}
