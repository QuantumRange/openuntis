package de.quantumrange.openuntis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class Configure implements WebFluxConfigurer {

	@Override
	public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
		configurer.defaultCodecs().maxInMemorySize(1024 * 1024 * 1024);
	}

}