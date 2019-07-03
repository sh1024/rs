package com.example.rsserver;

import com.example.rsserver.utils.LoggerUtils;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class RsServerApplication {
    private static Logger LOGGER = LoggerUtils.getLogger();

	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RsServerApplication.class, args);
        Environment env = context.getEnvironment();
        String port = env.getProperty("server.port");
        String[] activeProfiles = env.getActiveProfiles();
        String name = env.getProperty("info.build.name");
        String version = env.getProperty("info.build.version");
        LOGGER.info(
                "\n************************************************************************\n"
                        + "\t"
                        + name
                        + ":"
                        + version
                        + "\n"
                        + "\tListening on port: {}\n"
                        + "\tActive profiles  : {}\n"
//                        + "\tSwagger          : {}\n"
                        + "************************************************************************",
                port,
                activeProfiles
//              ,String.format("http://localhost:%s/swagger-ui.html", port)
        );
    }

}
