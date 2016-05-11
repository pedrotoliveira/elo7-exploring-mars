package br.com.elo7.mars.explorer.engine.bootstrap;

import br.com.elo7.mars.explorer.engine.domain.converter.InstructionActionReadConverter;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;

/**
 * Mongo Configurations
 * 
 * @author pedrotoliveira
 */
@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {
	
	@Value("${spring.data.mongodb.database}")
	private String databaseName;
	@Value("${spring.data.mongodb.host}")
	private String host;
	@Value("${spring.data.mongodb.port}")
	private int port;

	@Override
	protected String getDatabaseName() {
		return databaseName;
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient(host, port);
	}

	@Override
	public CustomConversions customConversions() {
		List<Converter<?, ?>> converters = new ArrayList<>();
		converters.add(new InstructionActionReadConverter());
		return new CustomConversions(converters);
	}
}
