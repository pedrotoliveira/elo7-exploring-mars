package br.com.elo7.mars.explorer.api.bootstrap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MarsExplorerApiApplication.class)
@WebAppConfiguration
public class MarsExplorerApiApplicationTests {

	@Test
	public void contextLoads() {
	}

}
