package org.dpppt.backend.sdk.config.ws.config;


import org.dpppt.backend.sdk.config.ws.controller.DPPPTConfigController;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DPPPTConfigControllerConfig {
	@Bean
	public DPPPTConfigController dppptSDKController() {
		return new DPPPTConfigController();
	}

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
	}

	@Bean
	public Flyway flyway() {
		Flyway flyWay = Flyway.configure().dataSource(dataSource()).locations("classpath:/db/migration/hsqldb").load();
		flyWay.migrate();
		return flyWay;
	}

	public String getDbType() {
		return "hsqldb";
	}

}
