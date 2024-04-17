package org.logger.logger;

import org.logger.logger.loggingFramework.LogStructure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class LoggerApplication {

	LogStructure logStructure = new LogStructure();

	public static void main(String[] args) {
		SpringApplication.run(LoggerApplication.class, args);
	}

//	Logger logger = LoggerFactory.getLogger(LoggerApplication.class);

}
